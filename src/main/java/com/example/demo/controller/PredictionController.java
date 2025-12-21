@RestController
@RequestMapping("/api/predict")
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionService service;

    @GetMapping("/restock-date/{stockRecordId}")
    public java.time.LocalDate getPrediction(@PathVariable Long stockRecordId) {
        return service.predictRestockDate(stockRecordId);
    }

    @PostMapping("/rules")
    public PredictionRule createRule(@RequestBody PredictionRule rule) {
        return service.createRule(rule);
    }
}