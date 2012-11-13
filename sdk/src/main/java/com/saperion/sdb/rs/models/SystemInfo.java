package com.saperion.sdb.rs.models;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SystemInfo extends Typed {

	private String version;
	private String buildTime;

	public SystemInfo() {
		super(ModelType.SYSTEM);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	@Override
	public String toString() {
		return "SystemInfo{" + "version='" + version + '\'' + ", buildTime='" + buildTime + '\''
				+ "} " + super.toString();
	}
}
