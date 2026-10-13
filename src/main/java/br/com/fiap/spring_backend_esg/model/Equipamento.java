package br.com.fiap.spring_backend_esg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_equipamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Equipamento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_tbl_equipamento"
    )
    @SequenceGenerator(
            name = "seq_tbl_equipamento",
            sequenceName = "seq_tbl_equipamento",
            allocationSize = 1
    )
    private Long id;

    private String nome;
    private String tipo;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Setor setor;
}
