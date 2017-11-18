package io.vertx.up.plugin.jdbc;

import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.plugin.Infix;
import io.vertx.up.plugin.document.MongoInfix;
import io.vertx.zero.func.HPool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class MySqlInfix implements Infix {

    private static final String NAME = "ZERO_MYSQL_POOL";

    private static final ConcurrentMap<String, SQLClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        HPool.exec(CLIENTS, name,
                () -> Infix.init(Plugins.Infix.MYSQL,
                        (config) -> MySQLClient.createShared(vertx, config, name),
                        MongoInfix.class));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public SQLClient get() {
        return CLIENTS.get(NAME);
    }
}
