package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data // This generates all getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer consumedQuantity;
    private LocalDateTime consumedDate;

    @ManyToOne
    @JoinColumn(name = "stock_record_id")
    private StockRecord stockRecord;
}