package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs") // [cite: 52]
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ConsumptionLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 54]
    @ManyToOne @JoinColumn(name = "stock_record_id")
    private StockRecord stockRecord; // [cite: 55]
    private Integer consumedQuantity; // [cite: 56]
    private LocalDate consumedDate; // [cite: 57]
}