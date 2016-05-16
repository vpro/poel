import Stapes from 'stapes';
import $ from 'jquery';

import FormEntry from '../models/FormEntry.js';
import FormText from '../models/FormText.js';
import FormatDate from '../../util/FormatDate.js';


/** HANDLEBARS + HELPERS */
import HandlebarsRuntime from 'handlebars-runtime';
import formTemplate from '../views/form.hbs!';
import formEntryTemplate from '../views/form-entry.hbs!';
import formTextTemplate from '../views/form-text.hbs!';

HandlebarsRuntime.registerPartial( 'entry', formEntryTemplate );
HandlebarsRuntime.registerPartial( 'text', formTextTemplate );

HandlebarsRuntime.registerHelper('formatDate', function ( date, format ) {
    return date.toLocaleString( format );
});

HandlebarsRuntime.registerHelper('formatResult', function ( result ) {
    return result.join(' - ');
});

HandlebarsRuntime.registerHelper('predictionEntry', function ( prediction, id, index ) {
    return ''.concat(
            '<input type="text" class="prediction" value="',
            ( ( prediction[ index ] !== -1 ) ? prediction[ index ] : '' ),
                '" name="prediction-'+ id +'-'+ index +'" id="prediction-'+ id +'-'+ index +'" />');
});

/** ****************************/
/** ****************************/


var FormController = Stapes.subclass({

    /**
     * @param {HTMLElement} container
     */
    constructor : function ( container ) {
        this.$container = $( container );

        this.bindViewHandlers();

        this.fetchForm().then( function ( form ) {

            this.form = form;
            this.render();

        }.bind( this ) );
    },

    bindViewHandlers: function () {

        this.$container.on( 'change', 'input[id^="prediction"]', this.handlePredictionChange.bind( this ) );
        this.$container.on( 'change', 'input[id^="joker"]', this.handleJokerChange.bind( this ) );

        this.$container.on( 'click', 'button', this.handleButtonClick.bind( this ) );
    },

    /**
     * Fetches the complete form from the API and parses it's entries
     */
    fetchForm: function () {

        var deferred = new $.Deferred();

        $.ajax({
            url: '/js/stub/form.json',
            dataType: 'json'
        }).then(
            function ( data ) {

                if ( data && data.elements ) {

                    deferred.resolve( this.parseForm( data ) );

                } else {
                    deferred.reject();
                }

            }.bind( this ),
            deferred.reject
        );

        return deferred.promise();
    },

    handleButtonClick: function ( e ) {
        var $button = $( e.currentTarget );
        var $formEntry = $button.parents('[data-id]').first();
        var id = $formEntry.data('id');

        e.preventDefault();

        if ( $button.is('[data-action="save"]') ) {
            // TODO: make it possible to save one piece of this form
        }

        if ( $button.is('[data-action="cancel"]') ) {

            $formEntry.replaceWith( formEntryTemplate(
                this.form.elements.filter( function ( elm ) {
                    return parseFloat( elm.id ) === id;
                }).pop()
            ) );
        }
    },

    handleJokerChange: function ( e ) {

        var $input = $( e.currentTarget );
        var $field = $input.parents('[data-id]').first();

        var id = $field.data('id');

        $field.addClass('form-entry_dirty');
    },

    handlePredictionChange: function ( e ) {

        var $input = $( e.currentTarget );
        var $field = $input.parents('[data-id]').first();

        var id = $field.data('id');

        $field.addClass('form-entry_dirty');
    },

    parseForm: function ( data ) {

        var parsed = {
            elements: []
        };

        data.elements.forEach( function ( element ) {

            switch ( element.type ) {

                case 'entry':
                    parsed.elements.push(
                        new FormEntry(
                            element.id,
                            element.contestants,
                            element.result,
                            element.prediction,
                            new FormatDate( element.dueDate ),
                            element.joker,
                            element.score
                        )
                    );
                    break;

                case 'text':
                    var text = new FormText();

                    if ( element.title ) {
                        text.setTitle( element.title );
                    }

                    if ( element.text ) {
                        text.setText( element.text );
                    }

                    parsed.elements.push( text );
                    break;
            }
        });

        return parsed;
    },

    render: function () {
        this.$container.html( formTemplate({
            elements: this.form.elements.map( function ( elm ) {
                return elm.toViewModel();
            })
        }) );
    }
});


export default FormController;