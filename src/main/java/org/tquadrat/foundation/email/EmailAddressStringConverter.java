/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.email;

import static java.lang.String.format;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.nonNull;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.StringConverter;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

/**
 *  <p>{@summary The implementation of
 *  {@link StringConverter}
 *  for an email address (implemented through
 *  {@link InternetAddress}).}</p>
 *  <p>The implementation of
 *  {@link #toString(Object)}
 *  uses
 *  {@link InternetAddress#toString()}
 *  to get the String representation of the email address.</p>
 *  <p>{@link #fromString(CharSequence)}
 *  calls the constructor
 *  {@link InternetAddress#InternetAddress(String)}</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: EmailAddressStringConverter.java 1108 2024-03-01 15:48:32Z tquadrat $
 *  @since 0.4.2
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: EmailAddressStringConverter.java 1108 2024-03-01 15:48:32Z tquadrat $" )
@API( status = STABLE, since = "0.4.2" )
public final class EmailAddressStringConverter implements StringConverter<InternetAddress>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The error message about an invalid email address: {@value}.
     */
    public static final String MSG_InvalidEmailAddress = "'%1$s' is not a valid email address";

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *  An instance of this class.
     */
    public static final EmailAddressStringConverter INSTANCE = new EmailAddressStringConverter();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code EmailAddressStringConverter}.
     */
    public EmailAddressStringConverter() { /* Just exists */ }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final InternetAddress fromString( final CharSequence source ) throws IllegalArgumentException
    {
        InternetAddress retValue = null;
        if( nonNull( source ) )
        {
            final var sourceString = source.toString();
            if( sourceString.isBlank() ) throw new IllegalArgumentException( format( MSG_InvalidEmailAddress, source ) );
            try
            {
                retValue = new InternetAddress( sourceString );
            }
            catch( final AddressException e )
            {
                throw new IllegalArgumentException( format( MSG_InvalidEmailAddress, source ), e );
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  fromString()

    /**
     *  This method is used by the
     *  {@link java.util.ServiceLoader}
     *  to obtain the instance for this
     *  {@link StringConverter}
     *  implementation.
     *
     *  @return The instance for this {@code StringConverter} implementation.
     */
    public static final EmailAddressStringConverter provider() { return INSTANCE; }
}
//  class EmailAddressStringConverter

/*
 *  End of File
 */