<#escape x as x!"">
<#macro pageUtils total  currentIndex  className pageUrl replaceStr miniflag>
	<#assign start_i =1>
	<#assign end_i = total>
	<#if ((currentIndex-button_padding_unit) gt 0)>
	<#assign start_i =currentIndex-button_padding_unit>
	</#if>
	<#if (max_distance+start_i) lte total>
	<#assign end_i = (max_distance+start_i)-1>
   	</#if>     	
   	<#if (start_i+max_distance-1) gt end_i>
   	<#assign start_i =end_i-max_distance+1>
   	</#if>   	
   	<#if start_i lte 0>
   	<#assign start_i = 1>
   	</#if>
   	
   	
   	<#if currentIndex==1>
   	<li class="prev disabled"><a href="javascript:void(0)"><i class="icon-double-angle-left"></i></a></li>
   	<#else>
   	<li class="page-wrap"><a href="${pageUrl?replace(replaceStr, (currentIndex-1)?string)}"><i class="icon-double-angle-left"></i></a></li>
   	</#if>
   	<#if (start_i-1) gt 0>
		<li><a  href='${pageUrl?replace(replaceStr, (start_i-1)?string)}'>...</a></li>
   	</#if>
	<#list start_i ..end_i as i>	
		<#if i == currentIndex>
			<li class="active"><a href='${pageUrl?replace(replaceStr, i?string)}'>${i}</a></li>
			<#else>
			<li><a href='${pageUrl?replace(replaceStr,i?string)}'>${i}</a></li>
		</#if>
	</#list>
 	<#if (end_i+1) lt total >
		<li><a href='${pageUrl?replace(replaceStr, (end_i+1)?string)}'>...</a></li>
   	</#if> 
   	<#if currentIndex==total>
   	<li><a href="javascript:void(0)"><i class="icon-double-angle-right"></i></a></li>
   	<#else>
   	<li><a href="${pageUrl?replace(replaceStr, (currentIndex+1)?string)}"><i class="icon-double-angle-right"></i></a></li>
   	</#if>
   	
   	<li class="page-btn"><a href="${pageUrl?replace(replaceStr, total?string)}">尾页</a></li>
   	<#if total gt 1>
   	<li> 共${total}页， </li>
    <li class="page-choose">到第 <input type="text" style="width:50px"  id="pageContext" name="page" > 页</li>
    <li class="page-btn"><a href="javascript:void(0);" id="pageButton">确定</a></li>
   <script type="text/javascript">
	$(document).ready(function(){
		$("#pageButton").click(function(){
			var totalPage = Number(${pages_total_page});
			var pageNo = $("#pageContext").val();
			//alert('$$$');
			if(Number(pageNo)>totalPage) {
			   alert("超过最大限制");
			} else if(Number(pageNo) > 0) {
			  window.location.href = "${pages_page_url}".replace("%23",pageNo);
			}
		});
	});
	</script>
   	</#if> 
</#macro>

	<#if page??>
		<#assign pages_page_url = pages_page_url?default( pageUrl )>
		
	<#if !pages_current_page?exists>
		<#if page??>
			<#assign pages_current_page = pages_current_page?default(page.page )>
		</#if>
	</#if>
	
	<#if !pages_total_page?exists>
		<#if page??>
			<#assign pages_total_page = pages_total_page?default( page.totalPages )>
		</#if>
	</#if>

	<#--<#assign pages_previous_page = pages_current_page - 1>
	<#assign pages_next_page = pages_current_page + 1> -->
	
	<#if pages_total_page lte 0>
	<#assign pages_total_page =1>
	</#if> 
	<#if pages_current_page gt pages_total_page>
	<#assign pages_current_page = pages_total_page>	
	</#if>
	
	<#if !pageMiniflag?exists>
	     <#assign pageMiniflag = 1>
		 <#assign button_padding_unit = 3>
		 <#assign max_distance = 5>
    <#else>
         <#assign button_padding_unit = 2>
		 <#assign max_distance = 3>
	</#if>
	
	<@pageUtils total=pages_total_page  currentIndex=pages_current_page  className="current" pageUrl=pages_page_url replaceStr="%23" miniflag=pageMiniflag/>
	
</#if>
</#escape>