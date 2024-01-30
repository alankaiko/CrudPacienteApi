package br.com.crud.acore.repository;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractModel, D extends AbstractFilter, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
