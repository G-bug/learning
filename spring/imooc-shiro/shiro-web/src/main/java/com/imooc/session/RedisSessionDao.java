package com.imooc.session;

import com.imooc.util.JedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDao extends AbstractSessionDAO {

    @Resource
    private JedisUtils jedisUtils;

    private final String SHIRO_SESSION_PERFIX = "imooc_shiro";

    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PERFIX + key).getBytes();
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtils.set(key, value);
            jedisUtils.expire(key, 600);
        }
    }

    protected Serializable doCreate(Session session) {
        Serializable sessionid = generateSessionId(session);
        assignSessionId(session, sessionid);
        saveSession(session);
        return sessionid;
    }

    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read session");
        if (sessionId == null) {
            return null;
        }

        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisUtils.get(key);
        return (Session) SerializationUtils.deserialize(value);
    }

    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            jedisUtils.del(key);
        }
    }

    public Collection<Session> getActiveSessions() {

        Set<byte[]> keys = jedisUtils.keys(SHIRO_SESSION_PERFIX);
        Set<Session> sessions = new HashSet<>();
        if (CollectionUtils.isEmpty(keys)) {
            return sessions;
        }

        for (byte[] key : keys) {
            Session session = (Session) SerializationUtils.deserialize(jedisUtils.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
