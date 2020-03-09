package exchange.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_curr_id", referencedColumnName = "id")
    private Currency from;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_curr_id", referencedColumnName = "id")
    private Currency to;

    @Column(name = "rate", nullable = false)
    private double rate;
}
