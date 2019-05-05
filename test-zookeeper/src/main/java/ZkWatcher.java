import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : dx
 * @date : 2019/5/5
 * Description :
 */
public class ZkWatcher implements Watcher {
    AtomicInteger seq = new AtomicInteger();
    static final String CONNECT_ADDR = "localhost:2181";
    static final int SESSION_OUTTIME = 50000;
    static final CountDownLatch countDownLatch = new CountDownLatch(1);
    static final CountDownLatch countDownLatch2 = new CountDownLatch(1);
    static final String PARENT_PATH = "/p";
    static final String CHILDREN_PATH = "/p/c";
    static final String LOG_PREFIX_OF_MAIN = "[Main]";
    ZooKeeper zk = null;

    public static void main(String[] args) throws InterruptedException {
        ZkWatcher zkWatcher = new ZkWatcher();
        zkWatcher.creatConnection(CONNECT_ADDR, SESSION_OUTTIME);

        Thread.sleep(1000);
        if (zkWatcher.createPath(PARENT_PATH, System.currentTimeMillis() + "", true)) {
            Thread.sleep(1000);
            zkWatcher.exists(PARENT_PATH, true);
            Thread.sleep(1000);
            zkWatcher.writeData(PARENT_PATH, "haha");
            Thread.sleep(1000);
            zkWatcher.getChildren(PARENT_PATH, true);
            Thread.sleep(1000);
            zkWatcher.createPath(CHILDREN_PATH, "", true);
            Thread.sleep(1000);

        }
        Thread.sleep(50000);
        // 清理节点
        zkWatcher.deleteAllTestPath();
        Thread.sleep(1000);
        zkWatcher.releaseConnection();

    }

    public void creatConnection(String connectionAddr, int sessionTimeout) {
        this.releaseConnection();
        try {
            zk = new ZooKeeper(connectionAddr, sessionTimeout, this);
            System.out.println(LOG_PREFIX_OF_MAIN + "开始链接zk。。。。。");
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("进入process....event = " + watchedEvent);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (watchedEvent == null) {
            return;
        }
        //连接状态
        Event.KeeperState status = watchedEvent.getState();
        //时间类型
        Event.EventType eventType = watchedEvent.getType();
        //收到影响的path
        String path = watchedEvent.getPath();
        String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";
        System.out.println(logPrefix + "收到Watcher通知");
        System.out.println(logPrefix + "连接状态：\t" + status.toString());
        System.out.println(logPrefix + "事件类型：\t" + eventType.toString());
        if (Event.KeeperState.SyncConnected.equals(status)) {
            //成功链接到zookeeper
            if (Event.EventType.None.equals(eventType)) {
                System.out.println(logPrefix + "成功连接到zookeeper服务器。。。");
                countDownLatch.countDown();
            } else if (Event.EventType.NodeCreated.equals(eventType)) {
                System.out.println(logPrefix + "节点创建。。。");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeDataChanged.equals(eventType)) {
                System.out.println(logPrefix + "节点数据更新。。。");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeChildrenChanged.equals(eventType)) {
                System.out.println(logPrefix + "子节点变更。。。");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeDeleted.equals(eventType)) {
                System.out.println(logPrefix + "节点删除。。。");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (Event.KeeperState.Disconnected.equals(status)) {
            System.out.println(logPrefix + "与服务器断开连接。。。");
        } else if (Event.KeeperState.AuthFailed.equals(status)) {
            System.out.println(logPrefix + "权限认证失败。。。");
        } else if (Event.KeeperState.Expired.equals(status)) {
            System.out.println(logPrefix + "会话失败。。。");
        }

        System.out.println("-----------------------------");


    }

    private void releaseConnection() {
        if (this.zk != null) {
            try {
                this.zk.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断指定节点是否存在
     *
     * @param path 节点路径
     */
    public Stat exists(String path, boolean needWatch) {
        try {
            return this.zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除所有节点
     */
    public void deleteAllTestPath() {
        if (this.exists(CHILDREN_PATH, false) != null) {
            this.deleteNode(CHILDREN_PATH);
        }
        if (this.exists(PARENT_PATH, false) != null) {
            this.deleteNode(PARENT_PATH);
        }
    }

    /**
     * 删除指定节点
     *
     * @param path 节点path
     */
    public void deleteNode(String path) {
        try {
            this.zk.delete(path, -1);
            System.out.println(LOG_PREFIX_OF_MAIN + "删除节点成功，path：" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建节点
     *
     * @param path 节点路径
     * @param data 数据内容
     * @return
     */
    public boolean createPath(String path, String data, boolean needWatch) {
        try {
            //设置监控(由于zookeeper的监控都是一次性的所以 每次必须设置监控)
            this.zk.exists(path, needWatch);
            System.out.println(LOG_PREFIX_OF_MAIN + "节点创建成功, Path: " +
                    this.zk.create(    /**路径*/
                            path,
                            /**数据*/
                            data.getBytes(),
                            /**所有可见*/
                            ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            /**永久存储*/
                            CreateMode.PERSISTENT) +
                    ", content: " + data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取指定节点数据内容
     *
     * @param path 节点路径
     * @return
     */
    public String readData(String path, boolean needWatch) {
        try {
            return new String(this.zk.getData(path, needWatch, null));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 更新指定节点数据内容
     *
     * @param path 节点路径
     * @param data 数据内容
     * @return
     */
    public boolean writeData(String path, String data) {
        try {
            System.out.println(LOG_PREFIX_OF_MAIN + "更新数据成功，path：" + path + ", stat: " +
                    this.zk.setData(path, data.getBytes(), -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取子节点
     *
     * @param path 节点路径
     */
    private List<String> getChildren(String path, boolean needWatch) {
        try {
            return this.zk.getChildren(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
