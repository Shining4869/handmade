<#escape x as x!"">
<#-- 搜索URL -->
<#macro searchPageUrl url values='' query=''>
<#if query != ''>
${URLUtils.searchUrl(url + "?" + query, values, false)}<#t>
<#else>
${URLUtils.searchUrl(url, values, false)}<#t>
</#if>
</#macro>
</#escape>

<#-- 搜索URL -->
<#macro searchUrl url values='' query='' encodeFlag=false>
<#if query != ''>
${URLUtils.searchUrl(url + "?" + query, values+"&pageNo=1", encodeFlag)}<#t>
<#else>
${URLUtils.searchUrl(url, values+"&pageNo=1", encodeFlag)}<#t>
</#if>
</#macro>

<#macro findRegionById regionId>
${URLUtils.getRegionById(regionId).regionChineseName}
</#macro>

<#macro findRegionById2 regionId str="律师">
${URLUtils.getRegionById(regionId).regionChineseName+str}
</#macro>

<#macro findRegionEnglishNameById regionId>
${URLUtils.getRegionById(regionId).regionEnglishName}
</#macro>

<#macro getDays from>
${URLUtils.getDays(from)}
</#macro>

<#macro showPriceUnit priceUnit=''>
<#if priceUnit?has_content>
<#if priceUnit == 'CNY'>RMB
<#elseif priceUnit == 'EUR'>欧元
<#elseif priceUnit == 'GBP'>英镑
<#elseif priceUnit == 'USD'>美元
<#else>
RMB
</#if>
<#else>
RMB
</#if>
</#macro>