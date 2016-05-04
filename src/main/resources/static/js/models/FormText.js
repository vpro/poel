import Stapes from 'stapes';


var FormText = Stapes.subclass({

    constructor : function () {

    },

    setText: function ( text ) {
        this.text = text;
    },

    setTitle: function ( title ) {
        this.title = title;
    },

    toViewModel: function () {
        return {
            type: 'text',
            title: this.title || false,
            text: this.text || false
        }
    }
});


export default FormText;