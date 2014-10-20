var KEYUP = 38;
var KEYDOWN = 40;
var KEYENTER = 13;
var KEYTAB = 9;

//var xmlHttp;
var suggestionsShown = false;
var currentlySelectedLi = -1;


/**
 * Deselects a specific li by changing the class/style to 'li'
 * 		-- Called by handleInput(e, userInput), newCurrentLi(li)
 * 
 * @param {Element} list The element 'suggestions' by tag li
 * @param {Integer} li The number of the li within the ul using 0 based index
 */
function deselectLi(list, li) {
    if(li != -1) {
        list[li].className = "li";
    }
}


/**
 * Empties the 'suggetions' div.  Sets the innerHTML to empty string and the display to none.
 * 		-- Called by body onclick
 */
function emptySuggestions() {

    document.getElementById("suggestions").innerHTML = "";
    document.getElementById("suggestions").style.display = 'none';
    
}


/**
 * Gets a reference to the current list
 * 
 * @return returns a reference to the list
 */
function getList() {
	
	return document.getElementById("suggestions").getElementsByTagName("li");
	
}


/**
 * Gets the xmlhttp object which is the keystone of AJAX.  The object type returned depends on the browser used.
 * 		-- Called by handleInput(e, userInput)
 * 
 * @return xmlHttp The xmlHttp object needed for the users browser.
 */
function getXmlHttpObject() {

	var xmlHttp = null;
	
	try {
		xmlHttp = new XMLHttpRequest();                           // Firefox, Opera 8.0+, Safari
	}
	catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");        // Internet Explorer
		}
		catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");     // Internet Explorer
		}
	}
	
	return xmlHttp;
	
}


/**
 * Handles a user clicking a suggestion
 * 		-- Called by li onclick
 * 
 * @param {String} selection The suggestion that the user clicked
 */
function handleClick(selection) {
	
	document.getElementById("inputText").value = unescape(selection);
	emptySuggestions();
	
}


/**
 * Handles a user typing in the inputText box.
 * 		-- Called by inputText onkeyup
 * 		-- EXIT IF the input is empty, the suggestions div will be emptied then the function will return
 * 		-- EXIT IF the user hit the KEYUP or KEYDOWN key, things will be updated accordingly abd the function will return
 * 
 * @param {Event} e The event which triggered the function call
 * @param {String} userInput The input entered by the user
 */
function handleInput(e, userInput) {
	var xmlHttp;
	var theEvent = e || window.event;

	if (userInput.length == 0) {
		emptySuggestions();
		return;
	}
	
	if (theEvent.keyCode == KEYUP || theEvent.keyCode == KEYDOWN) {
	
	    var list = getList();
	    
	    if(theEvent.keyCode == KEYDOWN) {
	        var newSelectedLi = currentlySelectedLi + 1;
	    }
	    else {
	        var newSelectedLi = currentlySelectedLi - 1;
	    }
	    
	    if(newSelectedLi <= 9 && newSelectedLi >= 0) {
	        selectLi(list, newSelectedLi, true);
	        deselectLi(list, currentlySelectedLi);
	        setCurrentLi(newSelectedLi);
	    }
	    
        return;
        
	}
	
	xmlHttp = getXmlHttpObject();
	
	if (xmlHttp == null) {
		alert("Your browser does not support this application.  Please update to a newer version.");
		return;
	} 
	
	var url = "suggest.jsp";
	var id=encodeURIComponent(document.getElementById("inputText").value);
        var parameters="id="+id;
	xmlHttp.onreadystatechange = function(){ 

if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {

        suggestionsShown = true;

        var split = xmlHttp.responseText.split(",");
        var suggestionList = "";

        if(split[0] != "") {

                for(var i = 0; i < split.length; i++) {
                        suggestionList += "<li value='" + i + "' class=\"li\" onmouseover=\"newCurrentLi(this.value);\" onclick=\"handleClick('" + escape(split[i]) + "');\">&nbsp;" + split[i].substr(0, 30) + "</li>";
                }

                if(suggestionList != "") {
                        suggestionList = "<ul class=\"ul\">" + suggestionList;
                        suggestionList += "</ul>";
                }

            document.getElementById("suggestions").innerHTML = suggestionList;
            document.getElementById("suggestions").style.display = 'block';

        }
        else {

            document.getElementById("suggestions").style.display = 'none';

        }

} 
	
};
	xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(parameters);
	
}


/**
 * Handles a user hitting the 'Tab' key while there are suggestions.
 * 		-- Called by body onkeydown
 * 
 * @param {Event} e The event which triggered the function call
 */
function handleTab(e) {
	
	var theEvent = e || window.event;
    
    if(theEvent.keyCode == KEYTAB && document.getElementById("suggestions").innerHTML != "") {
	setInputText();
        emptySuggestions();
        //showDetails();
    }
    
}


/**
 * Handles the user mousing over a new suggestion
 * 		-- Called by li onmouseover
 * 
 * @param {Integer} li The number of the li within the ul using 0 based index
 */
function newCurrentLi(li) {
    var list = getList();
    
    for(var i = 0; i < list.length; i++) {
        if(i == li) {
            selectLi(list, li, false);
            setCurrentLi(li);
        }
        else {
            deselectLi(list, i);
        }
    }
}


/**
 * Resets the currentlySelectedLi value to -1
 * 		-- Called by inputText onfocus
 */
function resetCurrentLi() {
    currentlySelectedLi = -1;
}


/**
 * Selects the new selected li.  Function will change the class to 'hoverLi' and will update the text of inputText if the updateInputText is true
 * 		-- Called by newCurrentLi(li), handleInput(e, userInput)
 * 
 * @param {Element} list The element 'suggestions' by tag li
 * @param {Integer} li The number of the li within the ul using 0 based index
 * @param {Boolean} updateInputText The boolean which tells the function if it should update the inputText box based on the new li
 */
function selectLi(list, li, updateInputText) {
    list[li].className = "hoverLi";
    
    if (updateInputText) {
        setInputText(li);
    }
}


/**
 * Sets the currentlySelectedLi variable based on the li passed in.  
 * 		-- Called by newCurrentLi(li), handleInput(e, userInput)
 * 
 * @param {Integer} li The number of the li within the ul using 0 based index
 */
function setCurrentLi(li) {
    currentlySelectedLi = li;
}


/**
 * Sets the inputText box based on the currentlySelectedLi
 * 
 * @param {Integer} li The number of the li within the ul using 0 based index (Optional)
 */
function setInputText(li) {
	
	var list = getList();
	
	if(li >= 0) {
                var split = list[li].innerHTML.substring(6).split("-")
		document.getElementById("inputText").value =split[0] ;
                document.getElementById("inputTextId").value =split[1] ;
	}
	else {
                var split= list[currentlySelectedLi].innerHTML.substring(6).split("-");
		document.getElementById("inputText").value =split[0] ;
                  document.getElementById("inputTextId").value =split[1] ;
	}  
}


