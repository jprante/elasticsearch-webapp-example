package org.xbib.elasticsearch.plugin.webapp.helper

import org.elasticsearch.ElasticsearchTimeoutException
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse
import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse
import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.node.Node
import org.junit.After
import org.junit.Before
import org.xbib.elasticsearch.webapp.NetworkUtils

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.atomic.AtomicInteger

import static org.elasticsearch.node.NodeBuilder.nodeBuilder
import static org.elasticsearch.common.settings.Settings.settingsBuilder

public abstract class AbstractNodeTestHelper {

    private final AtomicInteger counter = new AtomicInteger()

    private Map<String, Node> nodes = [:]

    Client client

    String cluster

    String host

    int port

    protected void setClusterName() {
        this.cluster = "test-webapp-cluster-" + NetworkUtils.getLocalAddress().getHostName() + "-" + counter.incrementAndGet()
    }

    protected static String getHome() {
        return System.getProperty("path.home")
    }

    protected Settings getNodeSettings() {
        return settingsBuilder()
                .put("cluster.name", cluster)
                .put("gateway.type", "none")
                .put("path.home", getHome())
                .build()
    }

    @Before
    public void startNode() throws Exception {
        setClusterName()
        startNode("1")
        findNodeAddress()
    }

    @After
    public void stopNode() throws Exception {
        if (nodes != null) {
            for (Node node : nodes.values()) {
                if (node != null) {
                    node.close()
                }
            }
            nodes.clear()
        }
        deleteFiles()
    }

    protected Node startNode(String id) {
        Node node = buildNode(id).start()
        this.client = node.client()
        waitForCluster(client, ClusterHealthStatus.YELLOW, TimeValue.timeValueSeconds(30))
        node
    }

    protected void findNodeAddress() {
        NodesInfoRequest nodesInfoRequest = new NodesInfoRequest().transport(true)
        NodesInfoResponse response = client.admin().cluster().nodesInfo(nodesInfoRequest).actionGet()
        Object obj = response.iterator().next().getTransport().getAddress().publishAddress()
        if (obj instanceof InetSocketTransportAddress) {
            InetSocketTransportAddress address = (InetSocketTransportAddress) obj
            host = address.address().getHostName()
            port = address.address().getPort()
            // TODO find http
        }
    }

    private Node buildNode(String id) throws IOException {
        Settings finalSettings = settingsBuilder()
                .put(getNodeSettings())
                .put("name", id)
                .build()
        Node node = nodeBuilder().settings(finalSettings).build()
        nodes.put(id, node)
        node
    }

    private static void deleteFiles() throws IOException {
        Path directory = Paths.get(getHome() + "/data")
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file)
                FileVisitResult.CONTINUE
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir)
                FileVisitResult.CONTINUE
            }

        })
    }

    private static void waitForCluster(Client client, ClusterHealthStatus status, TimeValue timeout) throws IOException {
        try {
            ClusterHealthResponse healthResponse =
                    client.admin().cluster().prepareHealth().setWaitForStatus(status).setTimeout(timeout).execute().actionGet()
            if (healthResponse != null && healthResponse.isTimedOut()) {
                throw new IOException("cluster state is " + healthResponse.getStatus().name()
                        + " and not " + status.name()
                        + ", cowardly refusing to continue with operations")
            }
        } catch (ElasticsearchTimeoutException e) {
            throw new IOException("timeout, cluster does not respond to health request, cowardly refusing to continue with operations")
        }
    }
}
