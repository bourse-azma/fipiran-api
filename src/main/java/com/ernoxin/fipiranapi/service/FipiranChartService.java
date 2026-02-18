package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranChartModels;
import com.ernoxin.fipiranapi.mapper.FipiranChartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranChartService {

    private final FipiranClient client;
    private final FipiranChartMapper mapper;

    public FipiranChartModels.IndexValueChartResult getIndexValueChart(String insCode, boolean showAll) {
        return mapper.toIndexValueChart(insCode, showAll,
                client.get("/services/chart/indexvaluechart", Map.of("insCode", insCode, "showAll", showAll)));
    }

    public FipiranChartModels.IndexEfficiencyChartResult getIndexEfficiencyChart(String insCodes, boolean showAll) {
        return mapper.toIndexEfficiencyChart(insCodes, showAll,
                client.get("/services/chart/indexefficiencychart", Map.of("insCodes", insCodes, "showAll", showAll)));
    }

    public FipiranChartModels.FundNavChartResult getFundNavChart(String regNo, boolean showAll) {
        return mapper.toFundNavChart(regNo, showAll,
                client.get("/services/chart/getfundchart", Map.of("regno", regNo, "showAll", showAll)));
    }

    public FipiranChartModels.FundNetAssetChartResult getFundNetAssetChart(String regNo, boolean showAll) {
        return mapper.toFundNetAssetChart(regNo, showAll,
                client.get("/services/chart/getfundnetassetchart", Map.of("regno", regNo, "showAll", showAll)));
    }

    public FipiranChartModels.FundEfficiencyChartResult getFundEfficiencyChart(String regNo, boolean showAll) {
        return mapper.toFundEfficiencyChart(regNo, showAll,
                client.get("/services/chart/fundefficiencychart", Map.of("regno", regNo, "showAll", showAll)));
    }

    public FipiranChartModels.FundPortfolioChartResult getPortfolioChart(String regNo, boolean showAll) {
        return mapper.toFundPortfolioChart(regNo, showAll,
                client.get("/services/chart/portfoliochart", Map.of("regno", regNo, "showAll", showAll)));
    }

    public FipiranChartModels.FundPossessionChartResult getPossessionChart(String regNo, boolean showAll) {
        return mapper.toFundPossessionChart(regNo, showAll,
                client.get("/services/chart/possessionchart", Map.of("regno", regNo, "showAll", showAll)));
    }

    public FipiranChartModels.FundAlphaBetaChartResult getAlphaBetaChart(String regNo, boolean showAll) {
        return mapper.toFundAlphaBetaChart(regNo, showAll,
                client.get("/services/chart/alphabetachart", Map.of("regno", regNo, "showAll", showAll)));
    }
}
