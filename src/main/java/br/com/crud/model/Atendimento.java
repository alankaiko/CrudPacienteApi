package br.com.crud.model;

import br.com.crud.acore.model.AbstractModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Atendimento extends AbstractModel {
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "tbl_hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @OneToOne
    @JoinColumn(name = "tbl_medico_id", referencedColumnName = "id")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "tbl_convenio_id", referencedColumnName = "id")
    private Convenio convenio;

    @OneToOne
    @JoinColumn(name = "tbl_assinador_id", referencedColumnName = "id")
    private Assinador assinador;
}
