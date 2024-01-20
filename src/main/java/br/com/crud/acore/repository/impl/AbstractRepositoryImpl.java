package br.com.crud.acore.repository.impl;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;
import br.com.crud.acore.repository.AbstractRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional(readOnly = true)
public class AbstractRepositoryImpl<T extends AbstractModel, D extends AbstractFilter, ID> extends SimpleJpaRepository<T, ID> implements AbstractRepository<T, D, ID> {
    private final EntityManager entityManager;
    protected Class<T> domainClass;
    protected Class<?> returnType;

    public AbstractRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        this(domainClass, domainClass, entityManager);
    }

    public AbstractRepositoryImpl(Class<T> domainClass, Class<?> returnType, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.returnType = returnType;
        this.entityManager = entityManager;
    }
}
