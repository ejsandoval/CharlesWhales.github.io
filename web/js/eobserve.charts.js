(function(window, $, undefined) {

    var NUM_OF_PROVIDERS = 4;
    var FLOAT_ACCURACY = 4;

    var period = getPeriod();

    // AJAX call to fetch provider prices for given period
    function fetchPrices(period, provider) {
        var deferred = $.Deferred();

        var fetching = $.getJSON('/eObserve/fetchPrices', {
            period: period,
            provider: provider
        });

        fetching.done(function(prices) {
            // strip redundant decimal digits
            prices = prices.map(function(price) {
                return price.toFixed(FLOAT_ACCURACY);
            });
            // reverse array
            prices = prices.reverse();
            deferred.resolve(prices);
        });

        return deferred.promise();
    }

    // Generate data in proper Chart.js format
    function generateChartData(period, provider, prices) {
        // generate labels first
        var labels = [];
        var i;
        if (period === 1) {
            for (i = 23; i >= 0; i--) {
                labels.push(moment().subtract(i, 'hours').format('h a'));
            }
        } else if (period <= 7) {
            for (i = period - 1; i >= 0; i--) {
                labels.push(moment().subtract(i, 'days').format('dddd'));
            }
        } else {
            for (i = period - 1; i >= 0; i--) {
                labels.push(moment().subtract(i, 'days').format('MMM Do'));
            }
        }
        // generate data needed by chart.js
        return {
            labels: labels,
            datasets: [
                {
                    label: 'Prices for provider ' + provider + ' during last ' + period + ' days',
                    fillColor: "rgba(220,220,220,0.2)",
                    strokeColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: prices
                }
            ]
        };
    }

    // Draw chart inside a <canvas> for given provider
    function drawChart(period, provider, prices) {
        var $canvas = $('#provider-chart-' + provider);
        // Get context with jQuery - using jQuery's .get() method.
        var ctx = $canvas.get(0).getContext('2d');
        // This will get the first returned node in the jQuery collection.
        var data = generateChartData(period, provider, prices);
        console.log(data);
        var myNewChart = new Chart(ctx).Line(data, {
            responsive: true,
            maintainAspectRatio: false
        });
    }

    // Fetch prices & draw chart for a given provider & period
    function fetchPricesAndDrawChart(period, provider) {
        fetching = fetchPrices(period, provider);

        fetching.done(function(prices) {
            drawChart(period, provider, prices);
        });
    }

    // Read period value from hidden <input>
    function getPeriod() {
        return parseInt($('#period-value').val(), 10);
    }

    // Show all charts
    function showPricesForAllProviders() {
        for (var i = 1; i <= NUM_OF_PROVIDERS; i++) {
            fetchPricesAndDrawChart(period, i);
        }
    }

    function adjustColWidths() {
        if (period < 15 && period !== 1) {
            $('.chart-col-container').addClass('col-md-6');
        }
    }

    adjustColWidths();
    showPricesForAllProviders();

})(this, jQuery);