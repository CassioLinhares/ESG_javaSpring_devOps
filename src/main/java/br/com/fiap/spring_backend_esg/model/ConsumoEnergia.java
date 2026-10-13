package br.com.fiap.spring_backend_esg.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_consumo_energia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConsumoEnergia {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_tbl_consumo_energia"
    )
    @SequenceGenerator(
            name = "seq_tbl_consumo_energia",
            sequenceName = "seq_tbl_consumo_energia",
            allocationSize = 1
    )
    private Long id;

    private Double consumoKwh;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;
}
