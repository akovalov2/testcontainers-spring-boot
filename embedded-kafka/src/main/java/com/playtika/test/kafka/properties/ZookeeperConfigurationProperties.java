/*
* The MIT License (MIT)
*
* Copyright (c) 2018 Playtika
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
 */
package com.playtika.test.kafka.properties;

import com.playtika.test.common.properties.CommonContainerProperties;
import com.playtika.test.common.utils.ContainerUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("embedded.zookeeper")
public class ZookeeperConfigurationProperties extends CommonContainerProperties {

    public static final String ZOOKEEPER_BEAN_NAME = "zookeeper";

    String zookeeperConnect;
    protected String containerZookeeperConnect;
    boolean enabled = true;
    int zookeeperPort = 0;
    int sessionTimeoutMs = 5_000;
    int socketTimeoutMs = 5_000;
    String dataFileSystemBind = "target/embedded-zk-data";
    String txnLogsFileSystemBind = "target/embedded-zk-txn-logs";
    String dockerImage = "confluentinc/cp-zookeeper:3.3.0";

    /**
     * Zookeeper container port will be assigned automatically if free port is available.
     * Override this only if you are sure that specified port is free.
     */
    @PostConstruct
    private void init() {
        if (this.zookeeperPort == 0) {
            this.zookeeperPort = ContainerUtils.getAvailableMappingPort();
        }
    }
}