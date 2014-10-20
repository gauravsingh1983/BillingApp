function showDetails(b)
{   
        var a = b[0];
	var xmlHttp=getXmlHttpObject();
	if(xmlHttp==null)
	{
		alert("Your browser does not support this function, please switch to Internet Explorer");
		return;
	}
	
	var url="getItemDetails.jsp";
	//encodeURIComponent(document.getElementById("inputTextId").value);
	var parameters="id="+a;
	//alert(id);
	xmlHttp.onreadystatechange=function(){
	if(xmlHttp.readyState==4)
	{
		if(xmlHttp.status==200)
		{
		
		var b = xmlHttp.responseXML;
		var root = b.documentElement;
		document.getElementById('inputRate').value =  b.getElementsByTagName('rate')[0].childNodes[0].nodeValue;
                 document.getElementById('inputCat').value =  b.getElementsByTagName('cat')[0].childNodes[0].nodeValue;
                 document.getElementById('inputVat').value =  b.getElementsByTagName('vat')[0].childNodes[0].nodeValue;
                
                
		}
	}
};
	
	xmlHttp.open("POST",url,true);
         xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(parameters);
}
//function stateChanged()

		
	