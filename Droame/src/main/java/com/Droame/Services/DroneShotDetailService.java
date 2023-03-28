package com.Droame.Services;

import java.util.List;

import com.Droame.Exception.NotFoundException;
import com.Droame.entities.DroneShotDetail;

public interface DroneShotDetailService {
    List<DroneShotDetail> getAllDroneShotDetails();
    DroneShotDetail getDroneShotDetailById(Long id) throws NotFoundException;
    void saveDroneShotDetail(DroneShotDetail droneShotDetail);
    void updateDroneShotDetail(Long id, DroneShotDetail droneShotDetail) throws NotFoundException;
    void deleteDroneShotDetail(Long id) throws NotFoundException;
}
