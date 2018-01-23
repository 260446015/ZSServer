package com.huishu.aitanalysis.common.analyzer.clean;

public enum CleanAlgorithm {	
	/**
	 * 替换内容中
	 * 包含下列内容做替换成空字符串
	 * (转载)|【转载】|（转载）|[转载]
	 */
	CleanTitleChars("负面分析");
	
	private CleanAlgorithm(String des){
        this.des = des;
    }
	
    private final String des;
    
    public String getDes() {
        return des;
    }
}
