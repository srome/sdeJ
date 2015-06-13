$(document).ready(function() {

    $('#theButton').click(function() {

            $.ajax({
                type: 'GET',
                url:  '/' + $('#eqSelect').val(),
                data: $('#attributes').serialize(),
                dataType: 'json',
                success:  function (data, status, jqXHR) {
                    var table = new google.visualization.DataTable();
                    table.addColumn('number', 'x');
                    table.addColumn('number', 'y');

                    var array = data.x;

                    console.log(array);
                    console.log(array.length);

                    for ( k=0; k < array.length; k++) {
                        table.addRow([parseFloat(data.x[k]), parseFloat(data.y[k])]);
                    }

                     var options = {
                                //title: 'Title'
                            };

                    var chart = new google.visualization.LineChart($('#chart').get(0));
                    chart.draw(table, options);


                }
            });
    });
});