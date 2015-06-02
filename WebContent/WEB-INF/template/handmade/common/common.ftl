<#escape x as x!"">
<#-- 搜索URL {pageNo} -->
<#macro searchPageUrl url values='' query=''>
<#if query != ''>
${URLUtils.searchUrl(url + "?" + query, values, false)}<#t>
<#else>
${URLUtils.searchUrl(url, values, false)}<#t>
</#if>
</#macro>
</#escape>

<#-- 搜索URL   ?xx&pageNo=1  -->
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

<#macro findRegionEnglishNameById regionId>
${URLUtils.getRegionById(regionId).regionEnglishName}
</#macro>

<#macro getDays from>
${URLUtils.getDays(from)}
</#macro>

<#macro getProductTypeName id>
${URLUtils.getProductTypeName(id)}
</#macro>

<#macro findProductCateNames cateIds>
${URLUtils.findProductCateNames(regionId)}
</#macro>


<#macro findProductSkyTitle jsonObject>
${URLUtils.change2ProProductSkuProMap(jsonObject).title}
</#macro>
<#macro findProductSkyWeight jsonObject>
${URLUtils.change2ProProductSkuProMap(jsonObject).weight}
</#macro>

<#macro findFirstProductImage cateCode>
${URLUtils.findFirstProductImage(cateCode)}
</#macro>



<#macro loadRegionName regionId>
${URLUtils.loadRegionName(regionId)}
</#macro>

<#macro loadCommentDesc productId orderId>
${(URLUtils.loadCommentDesc(productId,orderId).commentDesc)!''}
</#macro>
<#macro loadLcommentEvel productId orderId>
${(URLUtils.loadCommentDesc(productId,orderId).lcommentEvel)!''}
</#macro>

