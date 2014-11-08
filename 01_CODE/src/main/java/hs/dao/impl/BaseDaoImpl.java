package hs.dao.impl;

import hs.dao.BaseDaoI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

    SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

    @Override
    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    @Override
    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    @Override
    public Long count(String hql) {
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long) q.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql).setFirstResult((page - 1) * rows).setMaxResults(rows);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql).setFirstResult((page - 1) * rows).setMaxResults(rows);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findSQL(String hql) {
        Query q = this.getCurrentSession().createSQLQuery(hql);
        List<T> l = (ArrayList<T>) q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findSQL(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createSQLQuery(hql).setFirstResult((page - 1) * rows).setMaxResults(rows);
        List<T> l = (ArrayList<T>) q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findSQL(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createSQLQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = (ArrayList<T>) q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findSQL(String hql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createSQLQuery(hql).setFirstResult((page - 1) * rows).setMaxResults(rows);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = (ArrayList<T>) q.list();
        if (l != null && l.size() > 0) {
            return l;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByE(T t, String type) {
        List<T> results = (ArrayList<T>) this.getCurrentSession().createCriteria(type).add(Example.create(t)).list();
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    public java.util.List<T> findByE(T t, String type, int page, int rows) {
        List<T> results = (ArrayList<T>) this.getCurrentSession().createCriteria(type).setFirstResult((page - 1) * rows).setMaxResults(rows).add(Example.create(t)).list();
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    public java.util.List<T> findByE(T t, String type, int page, int rows, String sortName, String sortOrder) {
        if (sortOrder == "desc") {
            List<T> results = (ArrayList<T>) this.getCurrentSession().createCriteria(type).addOrder(Order.desc(sortName)).setFirstResult((page - 1) * rows).setMaxResults(rows).add(Example.create(t)).list();
            return results;
        } else {
            List<T> results = (ArrayList<T>) this.getCurrentSession().createCriteria(type).addOrder(Order.asc(sortName)).setFirstResult((page - 1) * rows).setMaxResults(rows).add(Example.create(t)).list();
            return results;
        }
    }

    @Override
    public Integer excuteHQL(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

    @Override
    public Integer excuteHQL(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }
}
