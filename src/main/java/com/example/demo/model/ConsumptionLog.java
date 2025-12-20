package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 54]

    @ManyToOne
    @JoinColumn(name = "stock_record_id")
    private StockRecord stockRecord; // [cite: 55]

    private Integer consumedQuantity; // Must be > 0 [cite: 56, 58]

    private LocalDate consumedDate; // Cannot be in the future [cite: 57, 58]
}