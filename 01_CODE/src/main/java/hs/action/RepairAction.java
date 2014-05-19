package hs.action;

import hs.service.RepairServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

@Action(value="repairAction")
public class RepairAction extends BaseAction{

	private RepairServiceI repairService;

	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	public void init (){
		repairService.repair();
	}

	
}
