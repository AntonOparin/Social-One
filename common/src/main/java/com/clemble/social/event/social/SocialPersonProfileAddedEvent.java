package com.clemble.social.event.social;

import com.clemble.social.data.social.SocialPersonProfile;

public class SocialPersonProfileAddedEvent extends AbstractSocialPersonProfileEvent {

    /**
     * Generated 03/09/2012
     */
    private static final long serialVersionUID = 6046537933029482030L;

    public SocialPersonProfileAddedEvent(SocialPersonProfile socialPersonProfile) {
        super(socialPersonProfile);
    }

}
