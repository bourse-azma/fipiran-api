package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranInstrumentModels;
import com.ernoxin.fipiranapi.mapper.FipiranInstrumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranInstrumentService {

    private final FipiranClient client;
    private final FipiranInstrumentMapper mapper;

    public FipiranInstrumentModels.InstrumentCompareResult compareInstruments(Map<String, Object> query) {
        return mapper.toCompareResult(client.get("/services/instrument/instrumentcompare", query));
    }

    public FipiranInstrumentModels.InstrumentSnapshotResult getInstrument(String insCode) {
        return mapper.toSnapshotResult(insCode, client.get("/services/instrument/getinstrument", Map.of("insCode", insCode)));
    }

    public FipiranInstrumentModels.EfficiencyResult getEfficiency(String insCode, String date) {
        return mapper.toEfficiencyResult(insCode, date,
                client.get("/services/instrument/getefficiency", Map.of("insCode", insCode, "date", date)));
    }

    public FipiranInstrumentModels.PeriodicStatisticsResult getPeriodicStatistics(String insCode, String date) {
        return mapper.toPeriodicStatsResult(insCode, date,
                client.get("/services/instrument/instrumentperiodicstatistics", Map.of("insCode", insCode, "date", date)));
    }

    public FipiranInstrumentModels.InstrumentHistoryResult getHistory(String insCode, int pageIndex, int pageSize) {
        return mapper.toHistoryResult(insCode, client.get("/services/instrument/instrumenthistory", Map.of(
                "insCode", insCode,
                "pageIndex", pageIndex,
                "pageSize", pageSize
        )));
    }

    public FipiranInstrumentModels.IndustryListResult getIndustries() {
        return mapper.toIndustryListResult(client.get("/services/instrument/getindustry"));
    }

    public FipiranInstrumentModels.SubIndustryListResult getSubIndustries() {
        return mapper.toSubIndustryListResult(client.get("/services/instrument/getindustrysub"));
    }
}
