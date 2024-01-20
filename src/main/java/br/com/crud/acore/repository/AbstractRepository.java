package br.com.crud.acore.repository;

import br.com.crud.acore.model.AbstractDTO;
import br.com.crud.acore.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity, D extends AbstractDTO, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
