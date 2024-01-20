package br.com.crud.model;

import br.com.crud.acore.model.AbstractModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AtendimentoExcluido extends AbstractModel {
    private String nome;
    private String procedimento;
    private String leito;
    private Date data;
    private Time hora;
    private String ph;
    private String po;
    private String pco;
    private String hco;
    private String co2total;
    private String be;
    private String o2sat;
    private String na;
    private String k;
    private String file;
    private String material;

    @OneToOne
    @JoinColumn(name = "tbl_hospital_codigo", referencedColumnName = "codigo")
    private Hospital hospital;

    @OneToOne
    @JoinColumn(name = "tbl_medico_codigo", referencedColumnName = "codigo")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "tbl_convenio_codigo", referencedColumnName = "codigo")
    private Convenio convenio;

    @OneToOne
    @JoinColumn(name = "tbl_assinador_codigo", referencedColumnName = "codigo")
    private Assinador assinador;

    private Date dataexclusao;
    private Time horaexclusao;
}
