// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

    // Create the data table.
    let data = new google.visualization.DataTable();
    data.addColumn('string', 'Answer');
    data.addColumn('number', 'Votes');
    data.addColumn({type:'string', role:'style'});
    data.addRows(chartData);

    // Set chart options
    let options = {
        width: 450,
        height: 350,
        legend:'none',
        allowHtml: true,
        backgroundColor: '#182a3e',
        chartArea: {
            width: '80%',
            height: '80%'
        },
        hAxis: {
            textStyle: {
                color: '#d5e1ef'
            }
        },
        vAxis: {
            baselineColor: '#d5e1ef',

            textStyle: {
                color: '#182a3e'
            },
            gridlines: {
                color: '#182a3e'
            },
            minorGridlines: {
                color: '#182a3e'
            }
        }
    };

    // Instantiate and draw our chart, passing in some options.
    let chart = new google.visualization.ColumnChart(document.getElementById('chart-div'));
    chart.draw(data, options);
}