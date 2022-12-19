package com.myproject.dataanalysys.repository;

import com.myproject.dataanalysys.entity.StatisticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsDataRepository extends JpaRepository<StatisticsData, Integer> {

    @Query(value =  "select * from statistics where city_id = :citi and year = :year", nativeQuery = true)
    StatisticsData findByCityAndYear(@Param(value = "citi") String citi, @Param(value = "year") int year);

    @Query(value = "select row_number() over () as id, baseQuery.year, baseQuery.cityid," +
            " subQuery2.avgmaxtemp avgmaxtemparature, subQuery3.avgmintemp avgmintemparature, subQuery1.sumofprecipitation" +
            " totalprecipitation from (select distinct extract(year from dateofcapture) as year, cityid from stationdata)" +
            " baseQuery Inner Join(select sum(precipitation) sumofprecipitation, extract(year from dateofcapture) as year," +
            " cityid from stationdata where precipitation != -9999 group by (year, cityid)) As subQuery1 ON baseQuery.year = subQuery1.year" +
            " and baseQuery.cityid = subQuery1.cityid Inner Join (select sum(maxtemp)/count(*) avgmaxtemp, extract(year from dateofcapture)" +
            " as year, cityid  from stationdata where stationdata.maxtemp != -9999 group by (year, cityid) ) As subQuery2" +
            " ON baseQuery.year = subQuery2.year and baseQuery.cityid = subQuery2.cityid Inner Join \n" +
            "(select sum(mintemp)/count(*) avgmintemp, extract(year from dateofcapture) as year, cityid  from stationdata" +
            " where stationdata.maxtemp != -9999 group by (year, cityid) ) As subQuery3 ON baseQuery.year = subQuery3.year" +
            " and baseQuery.cityid = subQuery3.cityid ", nativeQuery = true)
    public List<StatisticsData> getStatistics();
}
