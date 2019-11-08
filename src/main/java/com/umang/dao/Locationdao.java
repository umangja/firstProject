package com.umang.dao;

import java.util.List;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
import com.umang.model.Locationnew;

public interface Locationdao {

	public List<Locationnew> getAllLocation();

	public void addLocation(Locationnew loc);

	public List<Locationnew> getLocationofMedicine(int medicine_id);


}
