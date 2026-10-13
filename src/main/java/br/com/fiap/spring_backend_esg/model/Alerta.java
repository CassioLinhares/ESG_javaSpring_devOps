package br.com.fiap.spring_backend_esg.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_alerta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Alerta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_tbl_alerta"
    )
    @SequenceGenerator(
            name = "seq_tbl_alerta",
            sequenceName = "seq_tbl_alerta",
            allocationSize = 1
    )
    private Long id;

    private String mensagem;
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Setor setor;
}
