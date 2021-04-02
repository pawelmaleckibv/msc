package master.msc.services.impl;

import com.blueveery.core.model.BaseEntity;
import com.blueveery.core.services.BaseServiceImpl;
import master.msc.model.MscBaseEntity;
import master.msc.services.api.BaseObjectService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unchecked"})
public class BaseObjectServiceImpl<E extends MscBaseEntity> extends BaseServiceImpl<E> implements BaseObjectService<E> {

    private final Class entityType = (Class<?>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional
    public Long countAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = criteriaQuery.from(this.entityType);
        criteriaQuery.select(criteriaBuilder.countDistinct(root));
        TypedQuery<Long> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult()).orElse(0L);
    }

    @Transactional
    public List<E> findAllFromListById(List<UUID> uuidList) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.entityType);
        Root<E> root = criteriaQuery.from(this.entityType);
        Metamodel m = getEntityManager().getMetamodel();
        EntityType<E> E_ = m.entity(this.entityType);
        Predicate predicate = root.get(E_.getId(UUID.class)).in(uuidList);
        return getEntityManager().createQuery(criteriaQuery.where(predicate)).getResultList();
    }


}

