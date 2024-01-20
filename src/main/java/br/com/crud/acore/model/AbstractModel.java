package br.com.crud.acore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;

    private Boolean bloqueioEdicao;

    private Long idUsuarioBloqueio;

    private LocalDate dataBloqueio;
}
