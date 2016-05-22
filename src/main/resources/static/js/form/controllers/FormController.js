import Stapes from 'stapes';
import $ from 'jquery';

//import FormEntry from '../models/FormEntry.js';
//import FormText from '../models/FormText.js';
//import FormatDate from '../../util/FormatDate.js';


///** HANDLEBARS + HELPERS */
//import HandlebarsRuntime from 'handlebars-runtime';
//import formTemplate from '../views/form.hbs!';
//import formEntryTemplate from '../views/form-entry.hbs!';
//import formTextTemplate from '../views/form-text.hbs!';

//HandlebarsRuntime.registerPartial( 'entry', formEntryTemplate );
//HandlebarsRuntime.registerPartial( 'text', formTextTemplate );

//HandlebarsRuntime.registerHelper('formatDate', function ( date, format ) {
//    return date.toLocaleString( format );
//});

//HandlebarsRuntime.registerHelper('formatResult', function ( result ) {
//    return result.join(' - ');
//});

//HandlebarsRuntime.registerHelper('predictionEntry', function ( prediction, id, index ) {
//    return ''.concat(
//            '<input type="text" class="prediction" value="',
//            ( ( prediction[ index ] !== -1 ) ? prediction[ index ] : '' ),
//                '" name="prediction-'+ id +'-'+ index +'" id="prediction-'+ id +'-'+ index +'" />');
//});

/** ****************************/
/** ****************************/


var FormController = Stapes.subclass({

    /**
     * @param {HTMLElement} form
     */
    constructor : function ( form ) {

        this.$form = $( form );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );

        this.$matchPredictions = this.$form.find( '.match-prediction' );

        this.initialFormState = this.$form.serialize();

        this.bindViewHandlers();

        //        this.fetchForm().then( function ( form ) {

        //            this.form = form;
        //            this.render();

        //        }.bind( this ) );
    },

    bindViewHandlers: function () {

        // a value in the form has changed
        this.$form.change( function () {

            this.checkFormChanges();

            this.validateMatchPredictions();

        }.bind( this ) );

        this.$formReset.on( 'click', function () {

            setTimeout( function(){

                this.checkFormChanges();

                this.validateMatchPredictions();

            }.bind( this ), 1 );

        }.bind( this ) );

    },

    validateMatchPredictions: function () {

        this.$matchPredictions.each( function ( i, matchPrediction ) {

            var $matchPrediction = $( matchPrediction );

            var homePrediction = $matchPrediction.find( '.home-prediction' ).val();
            var awayPrediction = $matchPrediction.find( '.away-prediction' ).val();

            if( homePrediction.length < 1 && awayPrediction.length < 1 ) {

                // both fields are empty so there's no need to validate
                $matchPrediction.removeClass( 'not-valid' );

                // TODO: show how much time the user has left to predict the outcome before the match takes place (matchTime minus currentTime)

            } else {

                if(
                   ( homePrediction.length > 0 && awayPrediction.length > 0 )
                    && ( isNumeric( homePrediction ) && isNumeric( awayPrediction ) )
                    && ( homePrediction > -1 && awayPrediction > -1 )
                    && ( ( homePrediction.length > 1 && homePrediction.indexOf( 0 ) === 0 ) !== true )
                    && ( ( awayPrediction.length > 1 && awayPrediction.indexOf( 0 ) === 0 ) !== true )
                ) {
                    // All is well
                    $matchPrediction.removeClass( 'not-valid' );
                    //this.enableSubmit();
                } else {
                    $matchPrediction.addClass( 'not-valid' );
                    this.disableSubmit();
                }

            }

        }.bind( this ) );

    },

    checkFormChanges: function () {
        // check if form has changed relative to page load
        if( this.hasFormChangedSincePageLoad() ) {
            this.enableSubmit();
        } else {
            this.disableSubmit();
        }
    },

    enableSubmit: function () {
        this.$formSubmit.prop( 'disabled', false );
    },

    disableSubmit: function () {
        this.$formSubmit.prop( 'disabled', true );
    },

    hasFormChangedSincePageLoad: function () {

        return this.$form.serialize() !== this.initialFormState;
    },

//    /**
//     * Fetches the complete form from the API and parses it's entries
//     */
//    fetchForm: function () {
//
//        var deferred = new $.Deferred();
//
//        $.ajax({
//            url: '/js/stub/form.json',
//            dataType: 'json'
//        }).then(
//            function ( data ) {
//
//                if ( data && data.elements ) {
//
//                    deferred.resolve( this.parseForm( data ) );
//
//                } else {
//                    deferred.reject();
//                }
//
//            }.bind( this ),
//            deferred.reject
//        );
//
//        return deferred.promise();
//    },
//
//    handleButtonClick: function ( e ) {
//        var $button = $( e.currentTarget );
//        var $formEntry = $button.parents('[data-id]').first();
//        var id = $formEntry.data('id');
//
//        e.preventDefault();
//
//        if ( $button.is('[data-action="save"]') ) {
//            // TODO: make it possible to save one piece of this form
//        }
//
//        if ( $button.is('[data-action="cancel"]') ) {
//
//            $formEntry.replaceWith( formEntryTemplate(
//                this.form.elements.filter( function ( elm ) {
//                    return parseFloat( elm.id ) === id;
//                }).pop()
//            ) );
//        }
//    },
//
//    handleJokerChange: function ( e ) {
//
//        var $input = $( e.currentTarget );
//        var $field = $input.parents('[data-id]').first();
//
//        var id = $field.data('id');
//
//        $field.addClass('form-entry_dirty');
//    },
//
//    handlePredictionChange: function ( e ) {
//
//        var $input = $( e.currentTarget );
//        var $field = $input.parents('[data-id]').first();
//
//        var id = $field.data('id');
//
//        $field.addClass('form-entry_dirty');
//    },
//
//    parseForm: function ( data ) {
//
//        var parsed = {
//            elements: []
//        };
//
//        data.elements.forEach( function ( element ) {
//
//            switch ( element.type ) {
//
//                case 'entry':
//                    parsed.elements.push(
//                        new FormEntry(
//                            element.id,
//                            element.contestants,
//                            element.result,
//                            element.prediction,
//                            new FormatDate( element.dueDate ),
//                            element.joker,
//                            element.score
//                        )
//                    );
//                    break;
//
//                case 'text':
//                    var text = new FormText();
//
//                    if ( element.title ) {
//                        text.setTitle( element.title );
//                    }
//
//                    if ( element.text ) {
//                        text.setText( element.text );
//                    }
//
//                    parsed.elements.push( text );
//                    break;
//            }
//        });
//
//        return parsed;
//    },
//
//    render: function () {
//        this.$container.html( formTemplate({
//            elements: this.form.elements.map( function ( elm ) {
//                return elm.toViewModel();
//            })
//        }) );
//    }
});

function isNumeric ( n ) {
    return ! isNaN( parseFloat( n ) ) && isFinite( n );
}

export default FormController;