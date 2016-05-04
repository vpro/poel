import Stapes from 'stapes';
import $ from 'jquery';

import FormEntry from '../models/FormEntry.js';
import FormatDate from '../util/FormatDate.js';

import HandlebarsRuntime from 'handlebars-runtime';
import formTemplate from '../views/form.hbs!';

import formEntryTemplate from '../views/form-entry.hbs!';

HandlebarsRuntime.registerPartial( 'entry', formEntryTemplate );

HandlebarsRuntime.registerHelper('formatDate', function( date, format ) {
    return date.toLocaleString( format );
});

var FormController = Stapes.subclass({

    /**
     * @param {HTMLElement} container
     */
    constructor : function ( container ) {
        this.$container = $( container );

        this.fetchForm().then( function ( form ) {

            this.form = form;
            this.render();

        }.bind( this ) );
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

    parseForm: function ( data ) {

        var parsed = {
            elements: []
        };

        data.elements.forEach( function ( element ) {

            switch ( element.type ) {

                case 'entry':
                    parsed.elements.push(
                        new FormEntry(
                            element.title,
                            element.result,
                            element.gamble,
                            new FormatDate( element.dueDate ),
                            element.joker,
                            element.score
                        )
                    )
                    break;
            }
        });

        return parsed;
    },

    render: function () {
        this.$container.html( formTemplate({
            elements: this.form.elements.map( function ( elm ) {
                return elm.toJSON();
            })
        }) );
    }
});


export default FormController;