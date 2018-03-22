package net.idontrea.todo.control;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> 
{
	private static final String UNIT_NAME="todo";
	
	@PersistenceContext(unitName=UNIT_NAME)
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public GenericDAO(Class<T> entityClass)
	{
		this.entityClass=entityClass;
	}
	
	public T create(T entity)
	{
		em.persist(entity);
		return entity;
	}
	
	public void delete(T entity)
	{
		T remove=em.merge(entity);
		em.remove(remove);
	}
	
	public T update (T entity)
	{
		return em.merge(entity);
	}
	
	public T find(long entityID)
	{
		T ret=null;
		
		try
		{
			ret=em.find(entityClass,  entityID);
		}
		catch(NoResultException nre)
		{
			// Not an error
		}
		
		return ret;
	}
	
	public EntityManager getEntityManager()
	{
		return this.em;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll()
	{
		CriteriaQuery cq=em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
	
	public List<T> findAll(String namedQuery, Map<String, Object> parameters)
	{
		return findAll(namedQuery, parameters, 0);
	}
	
	@SuppressWarnings({"unchecked"})
	public List<T> findAll(String namedQuery, Map<String, Object> parameters, int limit)
	{
		List<T> ret=null;
		try
		{
			Query query=em.createNamedQuery(namedQuery);
			if(limit!=0)
			{
				query.setMaxResults(limit);
			}
			if(parameters!=null && !parameters.isEmpty())
			{
				this.populateQueryParameters(query, parameters);
			}
			ret=(List<T>)query.getResultList();
		}
		catch(NoResultException nre)
		{
			// Not an error
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public T findOneResult(String namedQuery, Map<String, Object> parameters)
	{
		T result=null;
		try
		{
			Query query=em.createNamedQuery(namedQuery);
		
			if(parameters!=null && !parameters.isEmpty())
			{
				populateQueryParameters(query, parameters);
			}
			
			result=(T)query.getSingleResult();
		}
		catch(NoResultException nre)
		{
			// Not an error
		}
		catch(Exception e)
		{
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public long countAll(String namedQuery, Map<String, Object> parameters)
	{
		long ret=0;
		Query query=em.createNamedQuery(namedQuery);
		populateQueryParameters(query, parameters);
		ret=(Long)query.getSingleResult();
		return ret;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters)
	{
		for(Entry<String, Object> entry : parameters.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
