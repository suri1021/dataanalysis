package com.myproject.dataanalysys.service;

import com.myproject.dataanalysys.entity.StatisticsData;
import com.myproject.dataanalysys.repository.StatisticsDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticDataServiceImpl implements  StatisticsDataService{

    private static final Logger log = LoggerFactory.getLogger(StatisticDataServiceImpl.class);
    @Autowired
    private StationDataService stationDataService;

    @Autowired
    private StatisticsDataRepository statisticsDataRepository;

    /***
     * Computes and updates the weather analysis data by year
     */
    @Override
    public void updateStatistics() {
        statisticsDataRepository.deleteAll();

        List<StatisticsData> statisticsDataList = getStatistics();

        statisticsDataRepository.saveAll(statisticsDataList);

  /*      List<Object[]> list = stationDataService.getDistinctListAndYear();

        ArrayList<StatisticsData> statisticsDataArrayList = new ArrayList<>();

        for (int i=0; i< list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String citi = (String) obj[0];
            int year = 0;

            if (obj[1] instanceof  Double)
                year = ((Double) obj[1]).intValue();

            else if (obj[1] instanceof  BigDecimal)
                year = ((BigDecimal) obj[1]).intValue();

            log.info("Computing statistics for citi " + citi + " year " + year);

            int avgMaxTemparature = stationDataService.getAvgMaxTemp(citi, year);
            int avgMinTemparature = stationDataService.getAvgMinTemp(citi, year);
            int sumOfPerception = stationDataService.getSumOfPerception(citi, year);

            StatisticsData statistics = new StatisticsData();
            statistics.setCityId(citi);
            statistics.setYear(year);
            statistics.setAvgMaxTemparature(avgMaxTemparature);
            statistics.setAvgMinTemparature(avgMinTemparature);
            statistics.setTotalPrecipitation(sumOfPerception);

            StatisticsData statisticsData = statisticsDataRepository.findByCityAndYear(citi, year);
            if (statisticsData != null)
            {
                statistics.setId(statisticsData.getId());
                statisticsDataArrayList.add(statistics);
            }
            else
                statisticsDataArrayList.add(statistics);
        }

        statisticsDataRepository.saveAll(statisticsDataArrayList);*/
    }

    @Override
    public List<StatisticsData> findAll() {
        return statisticsDataRepository.findAll();
    }

    @Override
    public List<StatisticsData> getStatistics() {
       return statisticsDataRepository.getStatistics();
    }
}
