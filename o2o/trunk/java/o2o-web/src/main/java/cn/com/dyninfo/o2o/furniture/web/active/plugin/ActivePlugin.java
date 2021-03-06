/*
 * Copyright (c) 2009-2016 SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 * All rights reserved.
 *
 * This file contains valuable properties of  SHENZHEN Eternal Dynasty
 * Technology Co.,Ltd.,  embodying  substantial  creative efforts  and
 * confidential information, ideas and expressions.    No part of this
 * file may be reproduced or distributed in any form or by  any  means,
 * or stored in a data base or a retrieval system,  without  the prior
 * written permission  of  SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 *
 */

package cn.com.dyninfo.o2o.furniture.web.active.plugin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.dyninfo.o2o.old.model.Active;
import cn.com.dyninfo.o2o.furniture.web.framework.plugin.AbstractPlugin;

@Component("activePlugin")
public class ActivePlugin extends AbstractActivePlugin {

	@Override
	public void addActiveAfter(Active active) {
		if(this.plugins!=null){
			for(AbstractPlugin plugin:this.plugins){
				if(plugin instanceof AbstractActivePlugin){
					AbstractActivePlugin p=(AbstractActivePlugin) plugin;
					p.addActiveAfter(active);
				}
			}
		}

	}

	@Override
	public Map<String, String> getGameParam(Active active) {
		Map map=new HashMap();
		if(this.plugins!=null){
			for(AbstractPlugin plugin:this.plugins){
				if(plugin instanceof AbstractActivePlugin){
					AbstractActivePlugin p=(AbstractActivePlugin) plugin;
					map.putAll(p.getGameParam(active));
				}
			}
		}
		return map;
	}

	@Override
	public String getName() {
		return "活动插件";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public void editActiveAfter(Active active) {
		if(this.plugins!=null){
			for(AbstractPlugin plugin:this.plugins){
				if(plugin instanceof AbstractActivePlugin){
					AbstractActivePlugin p=(AbstractActivePlugin) plugin;
					
					p.editActiveAfter(active);
				}
			}
		}
		
	}

}
