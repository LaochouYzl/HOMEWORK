

;(function( $ ) {

  // Browser supports HTML5 multiple file?
  var multipleSupport = typeof $('<input/>')[0].multiple !== 'undefined',
      isIE = /msie/i.test( navigator.userAgent )

  $.fn.idealFile = function() {

    return this.each(function() {

      var $file = $(this).addClass('ideal-file'), // the original file input
          // label that will be used for IE hack
          $wrap = $('<div class="ideal-file-wrap">'),
          $input = $('<input type="text" class="ideal-file-filename" />'),
          // Button that will be used in non-IE browsers
          $button = $('<button type="button" class="ideal-file-upload">Open</button>'),
          // Hack for IE
          $label = $('<label class="ideal-file-upload" for="'+ $file[0].id +'">Open</label>')

      // Hide by shifting to the left so we
      // can still trigger events
      $file.css({
        position: 'absolute',
        left: '-9999px'
      })

      $wrap.append( $input, ( isIE ? $label : $button ) ).insertAfter( $file )

      // Prevent focus
      $file.attr('tabIndex', -1)
      $button.attr('tabIndex', -1)

      $button.click(function () {
        $file.focus().click() // Open dialog
      })

      $file.change(function() {

        var files = [], fileArr, filename

        // If multiple is supported then extract
        // all filenames from the file array
        if ( multipleSupport ) {
          fileArr = $file[0].files
          for ( var i = 0, len = fileArr.length; i < len; i++ ) {
            files.push( fileArr[i].name )
          }
          filename = files.join(', ')

        // If not supported then just take the value
        // and remove the path to just show the filename
        } else {
          filename = $file.val().split('\\').pop()
        }

        $input.val( filename ) // Set the value
          .attr( 'title', filename ) // Show filename in title tootlip

      })

      $input.on({
        focus: function () { $file.trigger('change') },
        blur: function () { $file.trigger('blur') },
        keydown: function( e ) {
          if ( e.which === 13 ) { // Enter
            if ( !isIE ) { $file.trigger('click') }
          } else if ( e.which === 8 || e.which === 46 ) { // Backspace & Del
            // On some browsers the value is read-only
            // with this trick we remove the old input and add
            // a clean clone with all the original events attached
            $file.replaceWith( $file = $file.val('').clone( true ) )
            $file.trigger('change')
            $input.val('')
          } else if ( e.which === 9 ){ // TAB
            return
          } else { // All other keys
            return false
          }
        }
      })

    })

  }

}( jQuery ))


/**
 * @namespace Errors
 * @locale en
 */
$.idealforms.errors = {

  required: '此处是必填的.',
  number: '必须是数字.',
  digits: '必须是唯一的数字.',
  name: '必须至少有3个字符长，并且只能包含字母.',
  username: '必须在32之间和4个字符长，并以字母开头。(.)',
  pass: '必须至少6个字符长，并且至少包含一个数字，一个大写字母和一个小写字母.',
  strongpass: '必须至少为8个字符长，至少包含一个大写字母和一个小写字母和一个数字或特殊字符.',
  email: '必须是一个有效的e -mail地址. <em>(e.g. user@gmail.com)</em>',
  phone: 'Must be a valid US phone number. <em>(e.g. 555-123-4567)</em>',
  zip: 'Must be a valid US zip code. <em>(e.g. 33245 or 33245-0003)</em>',
  url: 'Must be a valid URL. <em>(e.g. www.google.com)</em>',
  minChar: 'Must be at least <strong>{0}</strong> characters long.',
  minOption: 'Check at least <strong>{0}</strong> options.',
  maxChar: 'No more than <strong>{0}</strong> characters long.',
  maxOption: 'No more than <strong>{0}</strong> options allowed.',
  range: 'Must be a number between {0} and {1}.',
  date: 'Must be a valid date. <em>(e.g. {0})</em>',
  dob: 'Must be a valid date of birth.',
  exclude: '"{0}" is not available.',
  excludeOption: '{0}',
  equalto: 'Must be the same value as <strong>"{0}"</strong>',
  extension: 'File(s) must have a valid extension. <em>(e.g. "{0}")</em>',
  ajaxSuccess: '<strong>{0}</strong> is not available.',
  ajaxError: 'Server error...'

}

/**
 * Get all default filters
 * @returns object
 */
var getFilters = function() {

  var filters = {

    equalto: {
      regex: function( input, value ) {
        var $equals = $( input.userOptions.data.equalto ),
            $input = input.input,
            name = $equals.attr('name') || $equals.attr('id'),
            isValid = $equals.parents('.ideal-field')
              .filter(function(){ return $(this).data('ideal-isvalid') === true })
              .length
        if ( !isValid ) { return false }
        this.error = $.idealforms.errors.equalto.replace( '{0}', name )
        return $input.val() === $equals.val()
      }
    },

    extension: {
      regex: function( input, value ) {
        var files = input.input[0].files || [{ name: value }],
            extensions = input.userOptions.data.extension,
            re = new RegExp( '\\.'+ extensions.join('|') +'$', 'i' ),
            valid = false
        for ( var i = 0, len = files.length; i < len; i++ ) {
          valid = re.test( files[i].name );
        }
        this.error = $.idealforms.errors.extension.replace( '{0}', extensions.join('", "') )
        return valid
      }
    },

    ajax: {
      regex: function( input, value, showOrHideError ) {

        var self = this
        var $input = input.input
        var userOptions = input.userOptions
        var name = $input.attr('name')
        var $field = $input.parents('.ideal-field')
        var valid = false

        var customErrors = userOptions.errors && userOptions.errors.ajax
        self.error = {}
        self.error.success = customErrors && customErrors.success
          ? customErrors.success
          : $.idealforms.errors.ajaxSuccess.replace( '{0}', value )
        self.error.fail = customErrors && customErrors.error
          ? customErrors.error
          : $.idealforms.errors.ajaxError

        // Send input name as $_POST[name]
        var data = {}
        data[ name ] = $.trim( value )

        // Ajax options defined by the user
        var userAjaxOps = input.userOptions.data.ajax

        var ajaxOps = {
          type: 'post',
          dataType: 'json',
          data: data,
          success: function( resp, text, xhr ) {
          console.log(resp)
            showOrHideError( self.error.success, true )
            $input.data({
              'ideal-ajax-resp': resp,
              'ideal-ajax-error': self.error.success
            })
            $input.trigger('change') // to update counter
            $field.removeClass('ajax')
            // Run custom success callback
            if( userAjaxOps._success ) {
              userAjaxOps._success( resp, text, xhr )
            }
          },
          error: function( xhr, text, error ) {
            if ( text !== 'abort' ) {
              showOrHideError( self.error.fail, false )
              $input.data( 'ideal-ajax-error', self.error.fail )
              $field.removeClass('ajax')
              // Run custom error callback
              if ( userAjaxOps._error ) {
                userAjaxOps._error( xhr, text, error )
              }
            }
          }
        }
        $.extend( ajaxOps, userAjaxOps )

        // Init
        $input.removeData('ideal-ajax-error')
        $input.removeData('ideal-ajax-resp')
        $field.addClass('ajax')

        // Run request and save it to be able to abort it
        // so requests don't bubble
        $.idealforms.ajaxRequests[ name ] = $.ajax( ajaxOps )
      }
    }

  }

  return filters

}

$.idealforms.flags = {
  noerror: function (i) {
    i.parent().siblings('.ideal-error').hide()
  },
  noicons: function (i) {
    i.siblings('.ideal-icon-valid, .ideal-icon-invalid').hide()
  },
  novalidicon: function (i) {
    i.siblings('.ideal-icon-valid').hide()
  },
  noinvalidicon: function (i) {
    i.siblings('.ideal-icon-invalid').hide()
  },
  noclass: function (i) {
    i.parents('.ideal-field').removeClass('valid invalid')
  },
  novalidclass: function (i) {
    i.parents('.ideal-field').removeClass('valid')
  },
  noinvalidclass: function (i) {
    i.parents('.ideal-field').removeClass('invalid')
  }
}

/*
 * Ideal Forms plugin
 */
var _defaults = {
  inputs: {},
  customFilters: {},
  customFlags: {},
  globalFlags: '',
  onSuccess: function(e) { alert('Thank you...') },
  onFail: function() { alert('Invalid!') },
  responsiveAt: 'auto',
  disableCustom: ''
}

// Constructor
var IdealForms = function( element, options ) {

  var self = this

  self.$form = $( element )
  self.opts = $.extend( {}, _defaults, options )

  self.$tabs = self.$form.find('section')

  // Set localized filters
  $.extend( $.idealforms.filters, getFilters() )

  self._init()

}

// Plugin
$.fn.idealforms = function( options ) {
  return this.each(function() {
    if ( !$.data( this, 'idealforms' ) ) {
      $.data( this, 'idealforms', new IdealForms( this, options ) )
    }
  })
}

// Get LESS variables
var LessVars = {
  fieldWidth: Utils.getLessVar( 'ideal-field-width', 'width' )
}

/*
 * Private Methods
 */
$.extend( IdealForms.prototype, {

  _init: function() {

    var self = this
    var o = self.opts
    var formElements = self._getFormElements()

    self.$form.css( 'visibility', 'visible' )
      .addClass('ideal-form')
      .attr( 'novalidate', 'novalidate' ) // disable HTML5 validation

    // Do markup
    formElements.inputs
      .add( formElements.headings )
      .add( formElements.separators )
      .each(function(){ self._doMarkup( $(this) ) })



    // Always show datepicker below the input
    if ( jQuery.ui ) {
      $.datepicker._checkOffset = function( a,b,c ) { return b }
    }

    // Add inputs specified by data-ideal
    // to the list of user inputs
    self.$form.find('[data-ideal]').each(function() {
      var userInput = o.inputs[ this.name ]
      o.inputs[ this.name ] = userInput || { filters: $(this).data('ideal') }
    })

   // Responsive
    if ( o.responsiveAt ) {
      $(window).resize(function(){ self._responsive() })
      self._responsive()
    }

    // Form events
    self.$form.on({
      keydown: function( e ) {
        // Prevent submit when pressing enter
        // but exclude textareas
        if ( e.which === 13 && e.target.nodeName !== 'TEXTAREA' ) {
          e.preventDefault()
        }
      },
      submit: function( e ) {
        if ( !self.isValid() ) {
          e.preventDefault()
          o.onFail()
          self.focusFirstInvalid()
        } else {
          o.onSuccess( e )
        }
      }
    })

    self._adjust()
    self._attachEvents()
    self.fresh() // Start fresh

  },

  _getFormElements: function() {
    return {
      inputs: this.$form.find('input, select, textarea, :button'),
      labels: this.$form.find('div > label:first-child'),
      text: this.$form.find('input:not([type="checkbox"], [type="radio"], [type="submit"]), textarea'),
      select: this.$form.find('select'),
      radiocheck: this.$form.find('input[type="radio"], input[type="checkbox"]'),
      buttons: this.$form.find(':button'),
      file: this.$form.find('input[type="file"]'),
      headings: this.$form.find('h1, h2, h3, h4, h5, h6'),
      separators: this.$form.find('hr'),
      hidden: this.$form.find('input:hidden')
    }
  },

  _getUserInputs: function() {
    return this.$form.find('[name="'+ Utils.getKeys( this.opts.inputs ).join('"], [name="') +'"]')
  },

  _getTab: function( nameOrIdx ) {
    var self = this
    var isNumber = !isNaN( nameOrIdx )
    if ( isNumber ) {
      return self.$tabs.eq( nameOrIdx )
    }
    return self.$tabs.filter(function() {
      var re = new RegExp( nameOrIdx, 'i' )
      return re.test( $(this).data('ideal-tabs-content-name') )
    })
  },

  _getCurrentTabIdx: function() {
    return this.$tabs.index( this.$form.find('.ideal-tabs-content:visible') )
  },

  _updateTabsCounter: function() {
    var self = this
    self.$tabs.each(function( i ) {
      var invalid = self.getInvalidInTab( i ).length
      self.$tabs.updateCounter( i, invalid )
    })
  },

  _adjust: function() {

    var self = this
    var o = self.opts
    var formElements = self._getFormElements()
    var curTab = self._getCurrentTabIdx()

    // Autocomplete causes some problems...
    formElements.inputs.attr('autocomplete', 'off')

    // Show tabs to calculate dimensions
    if ( self.$tabs.length ) { self.$tabs.show() }

    // Adjust labels
    var labels = formElements.labels
    labels.removeAttr('style').width( Utils.getMaxWidth( labels ) )

    // Adjust headings and separators
    if ( self.$tabs.length ) {
      this.$tabs.each(function(){
        $( this ).find('.ideal-heading:first').addClass('first-child')
      })
    } else {
      self.$form.find('.ideal-heading:first').addClass('first-child')
    }

    self._setDatepicker()

    // Done calculating hide tabs
    if ( self.$tabs.length ) {
      self.$tabs.hide()
      self.switchTab( curTab )
    }

  },

  _setDatepicker: function() {

    var o = this.opts
    var $datepicker = this.$form.find('input.datepicker')

    if ( jQuery.ui && $datepicker.length ) {

      $datepicker.each(function() {
        var userInput = o.inputs[ this.name ]
        var data = userInput && userInput.data && userInput.data.date
        var format = data ? data.replace( 'yyyy', 'yy' ) : 'mm/dd/yy'

        $(this).datepicker({
          dateFormat: format,
          beforeShow: function( input ) {
            $( input ).addClass('open')
          },
          onChangeMonthYear: function() {
            // Hack to fix IE9 not resizing
            var $this = $(this)
            var w = $this.outerWidth() // cache first!
            setTimeout(function() {
              $this.datepicker('widget').css( 'width', w )
            }, 1)
          },
          onClose: function() { $(this).removeClass('open') }
        })
      })

      // Adjust width
      $datepicker.on('focus keyup', function() {
        var t = $(this), w = t.outerWidth()
        t.datepicker('widget').css( 'width', w )
      })

      $datepicker.parent().siblings('.ideal-error').addClass('hidden')
    }
  },

  _doMarkup: function( $element ) {

    var o = this.opts
    var elementType = Utils.getIdealType( $element )

    // Validation elements
    var $field = $('<span class="ideal-field"/>')
    var $error = $('<span class="ideal-error" />')
    var $valid = $('<i class="ideal-icon ideal-icon-valid" />')
    var $invalid = $('<i class="ideal-icon ideal-icon-invalid"/>')
      .click(function(){
        $(this).parent().find('input:first, textarea, select').focus()
      })

    // Basic markup
    $element.closest('div').addClass('ideal-wrap')
      .children('label:first-child').addClass('ideal-label')

    var idealElements = {

      _defaultInput: function() {
        $element.wrapAll( $field ).after( $valid, $invalid )
          .parent().after( $error )
      },

      text: function() { idealElements._defaultInput() },

      radiocheck: function() {
        // Check if input is already wrapped so we don't
        // wrap radios and checks more than once
        var isWrapped = $element.parents('.ideal-field').length
        if ( !isWrapped ) {
          $element.parent().nextAll().andSelf().wrapAll( $field.addClass('ideal-radiocheck') )
          $element.parents('.ideal-field').append( $valid, $invalid ).after( $error )
        }
        if ( !/radiocheck/.test( o.disableCustom ) ) {
          $element.idealRadioCheck()
        }
      },

      select: function() {
        idealElements._defaultInput()
        if ( !/select/.test( o.disableCustom ) ) {
          $element.idealSelect()
        }
      },

      file: function() {
        idealElements._defaultInput()
        if ( !/file/.test( o.disableCustom ) ) {
          $element.idealFile()
        }
      },

      button: function() {
        if ( !/button/.test( o.disableCustom ) ) {
          $element.addClass('ideal-button')
        }
      },

      hidden: function() {
        $element.closest('div').addClass('ideal-hidden')
      },

      heading: function() {
        $element.closest('div').addClass('ideal-full-width')
        $element.parent().children().wrapAll('<span class="ideal-heading"/>')
      },

      separator: function() {
        $element.closest('div').addClass('ideal-full-width')
        $element.wrapAll('<div class="ideal-separator"/>')
      }

    }

    // Generate markup for current element type
    idealElements[ elementType ] ? idealElements[ elementType ]() : $.noop()

    $error.add( $valid ).add( $invalid ).hide() // Start fresh

  },


  /** Validates an input and shows or hides error and icon
   * @memberOf Actions
   * @param {object} $input jQuery object
   * @param {string} e The JavaScript event
   */
  _validate: function( $input, e ) {

    var self = this
    var o = this.opts

    var userOptions = o.inputs[ $input.attr('name') ]
    var userFilters = userOptions.filters && userOptions.filters.split(/\s/)
    var name = $input.attr('name')
    var value = $input.val()

    var ajaxRequest = $.idealforms.ajaxRequests[ name ]

    var isRadioCheck = $input.is('[type="checkbox"], [type="radio"]')

    var inputData = {
      // If is radio or check validate all inputs related by name
      input: isRadioCheck ? self.$form.find('[name="' + name + '"]') : $input,
      userOptions: userOptions
    }

    // Validation elements
    var $field = $input.parents('.ideal-field')
    var $error = $field.siblings('.ideal-error')
    var $invalid = isRadioCheck
      ? $input.parent().siblings('.ideal-icon-invalid')
      : $input.siblings('.ideal-icon-invalid')
    var $valid = isRadioCheck
      ? $input.parent().siblings('.ideal-icon-valid')
      : $input.siblings('.ideal-icon-valid')

    function resetError() {
      $field.removeClass('valid invalid').removeData('ideal-isvalid')
      $error.add( $invalid ).add( $valid ).hide()
    }

    function showOrHideError( error, valid ) {
      resetError()
      valid ? $valid.show() : $invalid.show()
      $field.addClass( valid ? 'valid' : 'invalid' )
      $field.data( 'ideal-isvalid', valid )
      if ( !valid ) {
        $error.html( error ).toggle( $field.is('.ideal-field-focus') )
      }
    }

    // Prevent validation when typing but not introducing any new characters
    // This is mainly to prevent multiple AJAX requests
    var oldValue = $input.data('ideal-value') || 0
    $input.data( 'ideal-value', value )
    if ( e.type === 'keyup' && value === oldValue ) { return false }

    // Validate
    if ( userFilters ) {

      $.each( userFilters, function( i, filter ) {

        var theFilter = $.idealforms.filters[ filter ]
        var customError = userOptions.errors && userOptions.errors[ filter ]
        var error = ''

        // If field is empty and not required
        if ( !value && filter !== 'required' ) {
          resetError()
          return false
        }

        if ( theFilter ) {

          // Abort and reset ajax if there's a request pending
          if ( e.type === 'keyup' && ajaxRequest ) {
            ajaxRequest.abort()
            $field.removeClass('ajax')
          }

          // AJAX
          if ( filter === 'ajax' ) {
            showOrHideError( error, false ) // set invalid till response comes back
            $error.hide()
            if ( e.type === 'keyup' ) {
              theFilter.regex( inputData, value, showOrHideError ) // runs the ajax callback
            } else {
              var ajaxError = $input.data('ideal-ajax-error')
              if ( ajaxError ) {
                showOrHideError( ajaxError, $input.data('ideal-ajax-resp') || false )
              }
            }
          }
          // All other filters
          else {
            var valid = Utils.isRegex( theFilter.regex ) && theFilter.regex.test( value ) ||
                        Utils.isFunction( theFilter.regex ) && theFilter.regex( inputData, value )
            error = customError || theFilter.error // assign error after calling regex()
            showOrHideError( error, valid )
            if ( !valid ) { return false }
          }
        }
      })
    }
    // Reset if there are no filters
    else {
      resetError()
    }

    // Flags
    var flags = (function(){
      var f = userOptions.flags && userOptions.flags.split(' ') || []
      if ( o.globalFlags ) {
        $.each( o.globalFlags.split(' '), function( i,v ) { f.push(v) })
      }
      return f
    }())
    if ( flags.length ) {
      $.each(flags, function( i,f ) {
        var theFlag = $.idealforms.flags[f]
        if ( theFlag ) { theFlag( $input, e.type ) }
      })
    }

    // Update counter
    if ( self.$tabs.length ) {
      self._updateTabsCounter( self._getCurrentTabIdx() )
    }
  },

  _attachEvents: function() {

    var self = this

    self._getUserInputs().on('keyup change focus blur', function(e) {

      var $this = $(this)
      var $field = $this.parents('.ideal-field')
      var isFile = $this.is('input[type=file]')

      // Trigger on change if type=file cuz custom file
      // disables focus on original file input (tabIndex = -1)
      if ( e.type === 'focus' || isFile && e.type === 'change' ) {
        $field.addClass('ideal-field-focus')
      }
      if ( e.type === 'blur' ) {
        $field.removeClass('ideal-field-focus')
      }

      self._validate( $this, e )
    })

  },

  _responsive: function() {

    var formElements = this._getFormElements()
    var maxWidth = LessVars.fieldWidth + formElements.labels.outerWidth()
    var $emptyLabel = formElements.labels.filter(function() {
      return $(this).html() === '&nbsp;'
    })
    var $customSelect = this.$form.find('.ideal-select')

    this.opts.responsiveAt === 'auto'
      ? this.$form.toggleClass( 'stack', this.$form.width() < maxWidth )
      : this.$form.toggleClass( 'stack', $(window).width() < this.opts.responsiveAt )

    var isStack = this.$form.is('.stack')
    $emptyLabel.toggle( !isStack )
    $customSelect.trigger( isStack ? 'list' : 'menu' )

    // Hide datePicker
    var $datePicker = this.$form.find('input.hasDatepicker')
    if ( $datePicker.length ) { $datePicker.datepicker('hide') }

  }

})

/*
 * Public Methods
 */
$.extend( IdealForms.prototype, {

  getInvalid: function() {
    return this.$form.find('.ideal-field').filter(function() {
      return $(this).data('ideal-isvalid') === false
    })
  },

  getInvalidInTab: function( nameOrIdx ) {
    return this._getTab( nameOrIdx ).find('.ideal-field').filter(function() {
      return $(this).data('ideal-isvalid') === false
    })
  },

  isValid: function() {
    return !this.getInvalid().length
  },

  isValidField: function( field ) {
    var $input = Utils.getByNameOrId( field )
    return $input.parents('.ideal-field').data('ideal-isvalid') === true
  },

  focusFirst: function() {
    if ( this.$tabs.length ) {
      this.$tabs.filter(':visible')
        .find('.ideal-field:first')
        .find('input:first, select, textarea').focus()
    } else {
      this.$form.find('.ideal-field:first')
        .find('input:first, select, textarea').focus()
    }
    return this
  },

  focusFirstInvalid: function() {
    var $first = this.getInvalid().first().find('input:first, select, textarea')
    var tabName = $first.parents('.ideal-tabs-content').data('ideal-tabs-content-name')
    if ( this.$tabs.length ) {
      this.switchTab( tabName )
    }
    $first.focus()
    return this
  },

  switchTab: function( nameOrIdx ) {
    this.$tabs.switchTab( nameOrIdx )
    return this
  },

  nextTab: function() {
    this.$tabs.nextTab()
    return this
  },

  prevTab: function() {
    this.$tabs.prevTab()
    return this
  },

  firstTab: function() {
    this.$tabs.firstTab()
    return this
  },

  lastTab: function() {
    this.$tabs.lastTab()
    return this
  },

  fresh: function() {
    this._getUserInputs().change().parents('.ideal-field')
      .removeClass('valid invalid')
    return this
  },

  freshFields: function( fields ) {
    fields = Utils.convertToArray( fields )
    $.each( fields, function( i ) {
      var $input = Utils.getByNameOrId( fields[ i ] )
      $input.change().parents('.ideal-field').removeClass('valid invalid')
    })
    return this
  },

  reload: function() {
    this._adjust()
    this._attachEvents()
    return this
  },

  reset: function() {

    var formElements = this._getFormElements()

    formElements.text.val('') // text inputs
    formElements.radiocheck.removeAttr('checked') // radio & check
    // Select and custom select
    formElements.select.find('option').first().prop( 'selected', true )
    this.$form.find('.ideal-select').trigger('reset')

    if ( this.$tabs.length ) { this.firstTab() }

    this.focusFirst().fresh()

    return this

  },

  resetFields: function( fields ) {

    fields = Utils.convertToArray( fields )
    var formElements = this._getFormElements()

    $.each( fields, function( i, v ) {
      var $input = Utils.getByNameOrId( v )
      var type = Utils.getIdealType( $input )
      if ( type === 'text' || type === 'file' ) {
        $input.val('')
      }
      if ( type === 'radiocheck' ) {
        $input.removeAttr('checked') // radio & check
      }
      if ( type === 'select' ) {
        $input.find('option').first().prop( 'selected', true )
        $input.next('.ideal-select').trigger('reset')
      }
      $input.change()
    })

    this.freshFields( fields )

    return this

  },

  toggleFields: function( fields ) {

    fields = Utils.convertToArray( fields )
    var self = this
    var $fields = Utils.getFieldsFromArray( fields )

    $fields.each(function() {
      var $this = $(this)
      var name = $this.attr('name') || $this.attr('id')
      var input = self.opts.inputs[ name ]
      var filters = input && input.filters
      var dataFilters = $this.data('ideal-filters') || ''
      $this.data( 'ideal-filters', filters )
      $this.closest('.ideal-wrap').toggle()
      self.setFieldOptions( name, { filters: dataFilters } )
    })

    return this
  },

  setOptions: function( options ) {
    $.extend( true, this.opts, options )
    this.reload().fresh()
    return this
  },

  setFieldOptions: function( name, options ) {
    $.extend( true, this.opts.inputs[ name ], options )
    this.reload().freshFields([ name ])
    return this
  },

  addFields: function( fields ) {

    fields = Utils.convertToArray( fields )

    var self = this

    // Save names of all inputs in Array
    // to use methods that take names ie. fresh()
    var allNames = []

    // Add an input to the DOM
    function add( ops ) {

      var name = ops.name

      var userOptions = {
        filters: ops.filters || '',
        data: ops.data || {},
        errors: ops.errors || {},
        flags: ops.flags || ''
      }

      var label = ops.label || ''
      var type = ops.type
      var list = ops.list || []
      var placeholder = ops.placeholder || ''
      var value = ops.value || ''

      var $field = $('<div>'+
          '<label>'+ label +':</label>'+
          Utils.makeInput( name, value, type, list, placeholder ) +
        '</div>')
      var $input = $field.find('input, select, textarea, :button')

      // Add inputs with filters to the list
      // of user inputs to validate
      if ( userOptions.filters ) { self.opts.inputs[ name ] = userOptions }

      self._doMarkup( $input )

      // Insert in DOM
      if ( ops.addAfter ) {
        $field.insertAfter(
          $( Utils.getByNameOrId( ops.addAfter ) ).parents('.ideal-wrap')
        )
      } else if ( ops.addBefore ) {
        $field.insertBefore(
          $(Utils.getByNameOrId( ops.addBefore ))
          .parents('.ideal-wrap')
        )
      } else if ( ops.appendToTab ) {
        $field.insertAfter(
          self._getTab( ops.appendToTab ).find('.ideal-wrap:last-child')
        )
      } else {
        $field.insertAfter( self.$form.find('.ideal-wrap').last() )
      }

      // Add current field name to list of names
      allNames.push( name )
    }

    // Run through each input
    $.each( fields, function( i, ops ) { add( ops ) })

    self.reload()
    self.freshFields( allNames )
    self._responsive()

    return this

  },

  removeFields: function( fields ) {
    fields = Utils.convertToArray( fields )
    var $fields = Utils.getFieldsFromArray( fields )
    $fields.parents('.ideal-wrap').remove()
    this.reload()
    return this
  }

})

}( jQuery, window, document ))
