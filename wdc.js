function init ()
{   
    //predefine connector methods

    var myConnector = tableau.makeConnector();

    //Method Tableau will use to fetch the schema

    myConnector.getSchema = function (schemaCallback)
    {

        /* 
                Define the schema columns
                Redefine depending on the data source
                For all options, see ColumnInfo in the WDC API
        */
        var cols = [
        { id : "name", alias : "name", 
            dataType : tableau.dataTypeEnum.string },
        { id : "weight", alias : "weight", 
            dataType : tableau.dataTypeEnum.float }
        ];


        //Defines the table schema
        var tableInfo = {
            id : "myData",
            alias : "Name and weight over time during fitness program",
            columns : cols
        };

        /*
            Defines the complete data schema
            Only one table, tableInfo, so the input array is a single element
        */

        schemaCallback([tableInfo]);
    };

    //Method Tableau will use to fetch the data

    myConnector.getData = function (table, doneCallback)
    {
        $.getJSON("http://localhost:8090/Tableau_WDC/app/service/query.json",
        function (resp)
        {   
            //Prepare the JSON response for parsing
            var stringified = JSON.stringify(resp);
            
            //Parse the stringified response
            var parsedResp = JSON.parse(stringified);
            
            var tableData = [];

            // Iterate over the JSON object
            for (var i = 0, len = parsedResp.length; i < len; i++) {
                tableData.push({
                    "name": parsedResp[i].name,
                    "weight": parsedResp[i].weight
                });

            }

            //Add to the Javascript table object
            table.appendRows(tableData);
            
            //Signal to Tableau that we are finished getting data
            doneCallback();

        });
        
    };

    // Validate the connector object before initialization
    tableau.registerConnector(myConnector);
};

function displayTableau () 
{
    //Send the connector object to Tableau for validation
    tableau.submit();
};

$(document).ready(function() {

    init();

});