package com.example.WebBanVe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	@Query("SELECT DISTINCT l.province FROM Location l")
	List<String> findDistinctProvince();

	@Query("SELECT DISTINCT l.district FROM Location l WHERE l.province = :province")
	List<String> findDistinctDistrictByProvince(String province);

	@Query("SELECT DISTINCT l.ward FROM Location l WHERE l.province = :province AND l.district = :district")
	List<String> findWardsByProvinceAndDistrict(@Param("province") String province, @Param("district") String district);

	@Query("SELECT l FROM Location l WHERE l.province = :province AND l.district = :district AND l.ward = :ward")
	List<Location> findLocationsByProvinceDistrictAndWard(@Param("province") String province,
			@Param("district") String district, @Param("ward") String ward);

	List<Location> findAllByNameNotNull();
}
