// CONFIG notes. Below are some comments that point to where this script can be customized.
// Note: Make sure to include a <tbody></tbody> in your table's HTML

var INPUT_NAME_PREFIX = 'inputName'; // this is being set via script
var INPUT_NAME_PREFIX1 = 'inputName1'; // this is being set via script
var INPUT_NAME_PREFIX2 = 'inputName2'; // this is being set via script
var INPUT_NAME_PREFIX3 = 'inputName3'; // this is being set via script
var INPUT_NAME_PREFIX4 = 'inputName4'; // this is being set via script
var INPUT_NAME_PREFIX5 = 'inputName5'; // this is being set via script
//var RADIO_NAME = 'totallyrad'; // this is being set via script
var TABLE_NAME = 'tblSample'; // this should be named in the HTML
var ROW_BASE = 1; // first number (for display)
var hasLoaded = false;

//window.onload=fillInRows;

function fillInRows()
{
	hasLoaded = true;
        
}

// CONFIG:
// myRowObject is an object for storing information about the table rows
function myRowObject(one, two, three, four, five, six, seven)
{
	this.one = one; // text object
	this.two = two; // input text object
	this.three = three; // input checkbox object
	this.four = four; // input radio object
         this.five = five; // input text object
         this.six = six; // input text object
         this.seven = seven; // input text object
}

/*
 * insertRowToTable
 * Insert and reorder
 */
function insertRowToTable()
{
    //alert(hasLoaded);
	if (hasLoaded) {
             //alert('akwe');
		var tbl = document.getElementById(TABLE_NAME);
		var rowToInsertAt =1 //tbl.tBodies[0].rows.length;
		/*for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
			if (tbl.tBodies[0].rows[i].myRow && tbl.tBodies[0].rows[i].myRow.four.getAttribute('type') == 'radio' && tbl.tBodies[0].rows[i].myRow.four.checked) {
				rowToInsertAt = i;
				break;
			}
		}*/
                
		addRowToTable(rowToInsertAt);
                // alert('akwe');
                reorderRows(tbl, rowToInsertAt);
                //alert('found');
	}
}

/*
 * addRowToTable
 * Inserts at row 'num', or appends to the end if no arguments are passed in. Don't pass in empty strings.
 */
function addRowToTable(num)
{ 
    
	if (hasLoaded) {
             
		var tbl = document.getElementById(TABLE_NAME);
		var nextRow = tbl.tBodies[0].rows.length;
		var iteration = nextRow + ROW_BASE;
                var itemval = document.getElementById("inputText").value.split('|');
                var qtyval = document.getElementById("inputQty").value;
                var rateval = document.getElementById("inputRate").value;
                var catval = document.getElementById("inputCat").value;
                var priceval = document.getElementById("inputPrice").value;
                var vatval = document.getElementById("inputVat").value;
		if (num == null) { 
			num = nextRow;
		} else {
			iteration = num + ROW_BASE;
		}
		
		// add the row
		var row = tbl.tBodies[0].insertRow(num);
		
		// CONFIG: requires classes named classy0 and classy1
		row.className = 'classy' + (iteration % 2);
	
		// CONFIG: This whole section can be configured
		
		// cell 0 - text
		var cell0 = row.insertCell(0);
		var textNode = document.createTextNode(iteration);
		cell0.appendChild(textNode);
		
		// cell 1 - input ITEM
		var cell1 = row.insertCell(1);
		var txtInp = document.createElement('input');
		txtInp.setAttribute('type', 'text');
		txtInp.setAttribute('name', INPUT_NAME_PREFIX );
		txtInp.setAttribute('size', '30');
		txtInp.setAttribute('value', itemval[1]); // iteration included for debug purposes
                txtInp.readOnly = true;
		cell1.appendChild(txtInp);
		
		// cell 2 - RATE
		var cell2 = row.insertCell(2);
		var txtInp1 = document.createElement('input');
		txtInp1.setAttribute('type', 'text');
		txtInp1.setAttribute('name', INPUT_NAME_PREFIX1 );
		txtInp1.setAttribute('size', '10');
		txtInp1.setAttribute('value', rateval); // iteration included for debug purposes
                txtInp1.readOnly = true;
		cell2.appendChild(txtInp1);
		
                 // cell 3 - CATEGORY 
                var cell3 = row.insertCell(3);
		var txtInp2 = document.createElement('input');
		txtInp2.setAttribute('type', 'text');
		txtInp2.setAttribute('name', INPUT_NAME_PREFIX2 );
		txtInp2.setAttribute('size', '20');
                txtInp2.setAttribute('value', catval);
                txtInp2.readOnly = true;
		cell3.appendChild(txtInp2); 
                
                 // cell 4 - QUANTITY 
                var cell4 = row.insertCell(4);
		var txtInp3 = document.createElement('input');
		txtInp3.setAttribute('type', 'text');
		txtInp3.setAttribute('name', INPUT_NAME_PREFIX3 );
		txtInp3.setAttribute('size', '10');
                txtInp3.setAttribute('value', qtyval);
                txtInp3.readOnly = true;
		cell4.appendChild(txtInp3); 
                
                // cell 5 - PRICES 
                var cell5 = row.insertCell(5);
		var txtInp4 = document.createElement('input');
		txtInp4.setAttribute('type', 'text');
		txtInp4.setAttribute('name', INPUT_NAME_PREFIX4 );
		txtInp4.setAttribute('size', '10');
                txtInp4.setAttribute('value', priceval);
                txtInp4.readOnly = true;
		cell5.appendChild(txtInp4);

                
                 // cell 7 - input button
		var cell6 = row.insertCell(6);
		var btnEl = document.createElement('input');
		btnEl.setAttribute('type', 'button');
		btnEl.setAttribute('value', 'Delete');
		btnEl.onclick = function () {deleteCurrentRow(this)};
		cell6.appendChild(btnEl);

                 // cell 6 - PRICES
                var cell7 = row.insertCell(7);
                cell7.className = 'classy2';
		var txtInp5 = document.createElement('input');
		txtInp5.setAttribute('type', 'hidden');
		txtInp5.setAttribute('name', INPUT_NAME_PREFIX5 );
                txtInp5.setAttribute('value', vatval);
		cell7.appendChild(txtInp5);

		// Pass in the elements you want to reference later
		// Store the myRow object in each row
		row.myRow = new myRowObject(textNode, txtInp, txtInp1, txtInp2, txtInp3, txtInp4,txtInp5);
	}
         reset();
         
}

// CONFIG: this entire function is affected by myRowObject settings
// If there isn't a checkbox in your row, then this function can't be used.
function deleteChecked()
{
	if (hasLoaded) {
		var checkedObjArray = new Array();
		var cCount = 0;
	
		var tbl = document.getElementById(TABLE_NAME);
		for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
			if (tbl.tBodies[0].rows[i].myRow && tbl.tBodies[0].rows[i].myRow.five.getAttribute('type') == 'checkbox' && tbl.tBodies[0].rows[i].myRow.five.checked) {
				checkedObjArray[cCount] = tbl.tBodies[0].rows[i];
				cCount++;
			}
		}
		if (checkedObjArray.length > 0) {
			var rIndex = checkedObjArray[0].sectionRowIndex;
			deleteRows(checkedObjArray);
			reorderRows(tbl, rIndex);
		}
	}
}

// If there isn't an element with an onclick event in your row, then this function can't be used.
function deleteCurrentRow(obj)
{
	if (hasLoaded) {
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
                reset();
		reorderRows(tbl, rIndex);
                
                
	}
         
}

function reorderRows(tbl, startingIndex)
{
    //alert("ad"+tbl.tBodies[0].rows.length);
	if (hasLoaded) {
		if (tbl.tBodies[0].rows[startingIndex]) {
			var count = startingIndex+ ROW_BASE;
			for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {
			
				// CONFIG: next line is affected by myRowObject settings
				tbl.tBodies[0].rows[i].myRow.one.data = count; // text
				
				// CONFIG: next line is affected by myRowObject settings
			     
				// CONFIG: next line is affected by myRowObject settings
				//var tempVal = tbl.tBodies[0].rows[i].myRow.two.value.split(" "); // for debug purposes
                                
				//tbl.tBodies[0].rows[i].myRow.two.value =  tempVal[0]; // for debug purposes
				
				// CONFIG: next line is affected by myRowObject settings
				//tbl.tBodies[0].rows[i].myRow.four.value = count; // input radio
				
				// CONFIG: requires class named classy0 and classy1
				tbl.tBodies[0].rows[i].className = 'classy' + (count % 2);
				
				count++;
			}
		}
           
	}
         
}

function deleteRows(rowObjArray)
{
	if (hasLoaded) {
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
}




 function reset(){
  // alert("gaurav");
  document.getElementById("inputText").value="";
  document.getElementById("inputRate").value="";
  document.getElementById("inputCat").value="";
  document.getElementById("inputQty").value="";
  document.getElementById("inputPrice").value="";
  document.getElementById("inputVat").value="";
  document.getElementById("inputText").focus();
  check1();
  
}

 function check1()
{  
    var tval=0.0;
    var wt1 = document.getElementsByName("inputName4").length
    //alert(wt1);
  if(wt1==1){
   tval=(tval-0)+(document.forms[0].elements["inputName4"].value-0);
  // alert("cond2");
}
var form=document.forms[0];
    var wtg=form["inputName4"];
    if(wt1>1){
 for(i=0;i<wt1;i++){
      tval = (tval-0)+(wtg[i].value-0);
   }    
}

 document.getElementById("tval").innerHTML=Math.round(tval*100)/100;
}   

