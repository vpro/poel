/**
 * @public
 * @class
 * Provides a way to create a human readable string from a Date instance in numerous formats
 *
 * @param {String|Number} [date=new Date()] - A value that the Date constructor understands (parseable date string, timestamp, ...)
 *  @see {@link https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Global_Objects/Date}
 */

var FormatDate = function ( date ) {

    this.date = ( date ) ? new Date( date ) : new Date();
};

FormatDate.prototype = {

    /**
     * @returns {Date} - The Date instance this FormatDate represents
     * @public
     */
    getRawDate : function () {
        return this.date;
    },

    /**
     * @returns {number} - the timestamp of the Date instance this FormatDate represents
     * @public
     */
    getTime : function () {
        return this.date.getTime();
    },

    /**
     * @param {String} format - A combination of  d, dd, ddd, dddd, M, MM, MMM, MMMM, yyy, yyyy, h, hh, m, mm, s, ss and separators
     * @returns {String} - The 'format' String representation of the date this FormatDate represents
     * @example
     *  var formatDate = new FormatDate();
     *  formatDate.toLocaleString('d') //  returns the date's day number
     *  formatDate.toLocaleString('dd') // returns the date's day number with a leading zero if the string length is below 2
     *  formatDate.toLocaleString('ddd') // returns the 3 character abbreviation of the date's weekday in Dutch, like 'zon' for 'zondag'.
     *  formatDate.toLocaleString('dddd') // returns the full weekday in Dutch, like 'zondag'
     *  formatDate.toLocaleString('M') // returns the number of the month
     *  formatDate.toLocaleString('MM') // returns the number of the month with a leading zero if the string length is below 2
     *  formatDate.toLocaleString('MMM') // returns the 3 character abbreviation of the month in Dutch, like 'apr' for 'april'
     *  formatDate.toLocaleString('MMMM') // returns the full name of the month in Dutch, like 'april'
     *  formatDate.toLocaleString('yyy') // returns the last two digits of the date's year prefixed by ', like '02 or '89
     *  formatDate.toLocaleString('yyyy') // returns the date's full year which might be 2020 but also 300 or 10
     *  formatDate.toLocaleString('h') // returns the date's hours
     *  formatDate.toLocaleString('hh') // returns the date's hours with a leading zero if the string length is below 2
     *  formatDate.toLocaleString('m') // returns the date's minutes
     *  formatDate.toLocaleString('mm') // returns the date's minutes with a leading zero if the string length is below 2
     *  formatDate.toLocaleString('s') // returns the date's seconds
     *  formatDate.toLocaleString('ss') // returns the date's seconds with a leading zero if the string length is below 2
     *
     *  // more complex
     *
     *  formatDate.toLocaleString('d MMMM, yyyy - hh:mm:ss') // the full Dutch date with clock
     *
     * @public
     */
    toLocaleString: function ( format ) {

        var tokens = format.match( /((y|M|d|h|m|s)+)|([^yMdhms]*)/g ); // Split the date keys from the separators

        if ( tokens && tokens.length ) {
            for( var x= 0, xl=tokens.length; x<xl; x++ ) {

                if( FormatDate.KEYS[ tokens[ x ]] ) {
                    tokens[ x ] = FormatDate.KEYS[ tokens[ x ]].apply( this.date );
                }

            }
        } else {
            tokens = [ '' ];
        }

        return tokens.join('');
    }
};

FormatDate.addLeadingZeroes = function ( n ) {
    var c = 2;

    return ( ( n = n + '').length < c ) ? new Array( ( ++c ) - n.length ).join('0') + n : n;
};

FormatDate.DAYS         = [ 'zondag', 'maandag', 'dinsdag', 'woensdag', 'donderdag', 'vrijdag', 'zaterdag' ];
FormatDate.MONTHS       = [ 'januari', 'februari', 'maart', 'april', 'mei', 'juni', 'juli', 'augustus', 'september', 'oktober', 'november', 'december' ];

FormatDate.KEYS = {
    yyyy : function () {
        return this.getFullYear()+'';
    },
    yyy : function () {
        var fullYear = this.getFullYear() +'';

        return '\''+ ( ( fullYear.length > 2 ) ? fullYear.substr( -2 ) : FormatDate.addLeadingZeroes( fullYear ) );
    },
    MMMM : function () {
        return FormatDate.MONTHS[ this.getMonth() ];
    },
    MMM : function () {
        var month = this.getMonth();

        return month === 2 ? 'mrt' : FormatDate.MONTHS[ month ].substr( 0, 3 );
    },
    MM : function () {
        return FormatDate.addLeadingZeroes( this.getMonth()+1 );
    },
    M : function () {
        return ( this.getMonth()+1 )+'';
    },
    dddd : function () {
        return FormatDate.DAYS[ this.getDay() ];
    },
    ddd : function () {
        return FormatDate.DAYS[ this.getDay() ].substr( 0, 2 );
    },
    dd : function () {
        return FormatDate.addLeadingZeroes( this.getDate() );
    },
    d  : function () {
        return this.getDate()+'';
    },
    hh : function () {
        return FormatDate.addLeadingZeroes( FormatDate.KEYS[ 'h' ].apply( this ) );
    },
    h : function () {
        return this.getHours()+'';
    },
    mm : function () {
        return FormatDate.addLeadingZeroes( FormatDate.KEYS[ 'm' ].apply( this ) );
    },
    m : function () {
        return this.getMinutes()+'';
    },
    ss: function () {
        return FormatDate.addLeadingZeroes( FormatDate.KEYS[ 's' ].apply( this ) );
    },
    s : function () {
        return this.getSeconds()+'';
    }
};

export default FormatDate;
