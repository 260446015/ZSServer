$(function() {
    (function($) {
        $.fn.jalendar = function(options) {

            var settings = $.extend({
                customDay: new Date(),
                color: '#65c2c0',
                lang: 'EN'
            }, options);
            // Languages            
            var dayNames = {};
            var monthNames = {};
            var lAddEvent = {};
            var lAllDay = {};
            var lTotalEvents = {};
            var lEvent = {};
            dayNames['EN'] = new Array('一', '二', '三', '四', '五', '六', '日');
            dayNames['TR'] = new Array('Pzt', 'Sal', 'Çar', 'Per', 'Cum', 'Cmt', 'Pzr');
            dayNames['ES'] = new Array('Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Såb', 'Dom');
            monthNames['EN'] = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
            monthNames['TR'] = new Array('Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran', 'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık');
            monthNames['ES'] = new Array('Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre');
            lAddEvent['EN'] = 'Add New Event';
            lAddEvent['TR'] = 'Yeni Etkinlik Ekle';
            lAddEvent['ES'] = 'Agregar Un Nuevo Evento';
            lAllDay['EN'] = 'All Day';
            lAllDay['TR'] = 'Tüm Gün';
            lAllDay['ES'] = 'Todo El Día';
            lTotalEvents['EN'] = 'Total Events in This Month: ';
            lTotalEvents['TR'] = 'Bu Ayki Etkinlik Sayısı: ';
            lTotalEvents['ES'] = 'Total De Eventos En Este Mes: ';
            lEvent['EN'] = 'Event(s)';
            lEvent['TR'] = 'Etkinlik';
            lEvent['ES'] = 'Evento(s)';


            var $this = $(this);
            var div = function(e, classN) {
                return $(document.createElement(e)).addClass(classN);
            };

            var clockHour = [];
            var clockMin = [];
            for (var i = 0; i < 24; i++) {
                clockHour.push(div('div', 'option').text(i))
            }
            for (var i = 0; i < 59; i += 5) {
                clockMin.push(div('div', 'option').text(i))
            }

            // HTML Tree
            $this.append(
                div('div', 'jalendar-wood').append(
                    div('div', 'jalendar-pages').append(
                        div('div', 'header').append(
                            div('div', 'day-names')
                        ),
                        div('div', 'days')
                    )
                )
            );

            // Adding day boxes
            for (var i = 0; i < 42; i++) {
                $this.find('.days').append(div('div', 'day'));
            }

            // Adding day names fields
            for (var i = 0; i < 7; i++) {
                $this.find('.day-names').append(div('h2').text(dayNames[settings.lang][i]));
            }

            var d = new Date(settings.customDay);
            var year = d.getFullYear();
            var date = d.getDate();
            var month = d.getMonth();

            var isLeapYear = function(year1) {
                var f = new Date();
                f.setYear(year1);
                f.setMonth(1);
                f.setDate(29);
                return f.getDate() == 29;
            };

            var feb;
            var febCalc = function(feb) {
                if (isLeapYear(year) === true) { feb = 29; } else { feb = 28; }
                return feb;
            };
            var monthDays = new Array(31, febCalc(feb), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

            function calcMonth() {

                monthDays[1] = febCalc(feb);

                var weekStart = new Date();
                weekStart.setFullYear(year, month, 0);
                var startDay = weekStart.getDay();

                $this.find('.header h1').html(monthNames[settings.lang][month] + ' ' + year);

                $this.find('.day').html('&nbsp;');
                $this.find('.day').removeClass('this-month');
                for (var i = 1; i <= monthDays[month]; i++) {
                    startDay++;
                    $this.find('.day').eq(startDay - 1).addClass('this-month').attr('data-date', i + '/' + (month + 1) + '/' + year).html('').append(div('span', '').html(i));
                }

                $this.find('.added-event').each(function(i) {
                    $(this).attr('data-id', i);
                    $this.find('.this-month[data-date="' + $(this).attr('data-date') + '"]').append(
                        div('div', 'event-single').attr('data-id', i).append(
                            div('p', '').text($(this).attr('data-place') + $(this).attr('data-place-val')),
                            div('p', '').text($(this).attr('data-sponsor') + $(this).attr('data-sponsor-val')),
                            div('em', '')
                        )
                    );
                    $this.find('.day').has('.event-single').addClass('have-event').prepend(div('i', ''));
                });

                calcTotalDayAgain();

            }

            calcMonth();



            // Calculate total event again
            function calcTotalDayAgain() {
                var eventCount = $this.find('.this-month .event-single').length;
                $this.find('.total-bar b').text(eventCount);
                $this.find('.events h3 span b').text($this.find('.events .event-single').length)
            }

            function prevAddEvent() {
                $this.find('.day span').removeClass('selected').removeAttr('style');
            }



            $this.on('click', '.have-event', function() {
                var eventSingle = $(this).find('.event-single')
                $this.find('.events .event-single').remove();
                prevAddEvent();
                var left = parseInt($(this).find('span').offset().left),
                    right = $(document).width() - left - 14;
                var width = $(this).find('.event-single').outerWidth();
                if (left < width / 2) {
                    $(this).find('.event-single').css({ 'left': 'initial', 'transform': 'translateX(-' + (left - 5) + 'px)' })
                    $(this).find('em').css('left', left + 14 + 'px')
                } else if (right < width / 2) {
                    $(this).find('.event-single').css({ 'left': 'initial', 'transform': 'translateX(-' + (width - right - 18) + 'px)' })
                    $(this).find('em').css({ 'left': 'initial', 'right': right - 14 + 'px' });
                }
                $(this).find('span').addClass('selected').css({ 'background-color': '#00C0FF', 'color': '#fff' });
                $this.find('.event-single').hide();
                $(this).find('.event-single').show();

            });





        };

    }(jQuery));

});