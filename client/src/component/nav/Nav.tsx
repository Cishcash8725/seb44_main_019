import React, { useState } from 'react';

import {
  NavWrapper,
  NavContainer,
  Logo,
  NavItemContentWrapper,
  NavItems,
  NavItem,
  NavItemContent,
} from './Nav.styled';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen } from '@fortawesome/free-solid-svg-icons';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faBox } from '@fortawesome/free-solid-svg-icons';
import { faPersonRunning } from '@fortawesome/free-solid-svg-icons';
import { faHandPointRight } from '@fortawesome/free-solid-svg-icons';
import { faMessage } from '@fortawesome/free-solid-svg-icons';
import { faArrowRightToBracket } from '@fortawesome/free-solid-svg-icons';
import { faPeopleCarryBox } from '@fortawesome/free-solid-svg-icons';

import { Link } from 'react-router-dom';
import Avatar from '../../common/avatar/Avatar';

const Nav: React.FC = () => {
  const [isNavItemContent, setIsNavItemContent] = useState(false);

  const handleNavItemHover = () => {
    setIsNavItemContent(true);
  };

  const handleNavItemNotHover = () => {
    setIsNavItemContent(false);
  };
  return (
    <NavWrapper>
      <NavContainer>
        <Link to="/main">
          <Logo
            src="https://cdn-icons-png.flaticon.com/512/499/499857.png"
            alt="Logo"
          ></Logo>
        </Link>

        <NavItems>
          <Link to="/shellcreate" style={{ textDecoration: 'none' }}>
            <NavItem>
              <FontAwesomeIcon icon={faPen} />
              Create Shells
            </NavItem>
          </Link>
          <NavItem
            onMouseEnter={handleNavItemHover}
            onMouseLeave={handleNavItemNotHover}
          >
            <FontAwesomeIcon icon={faMagnifyingGlass} />
            Find Shells
          </NavItem>
          {isNavItemContent && (
            <NavItemContentWrapper
              onMouseEnter={handleNavItemHover}
              onMouseLeave={handleNavItemNotHover}
            >
              <Link to="/shelllist/product" style={{ textDecoration: 'none' }}>
                <NavItemContent>
                  <FontAwesomeIcon icon={faBox} />
                  Product
                </NavItemContent>
              </Link>
              <Link to="/shelllist/talent" style={{ textDecoration: 'none' }}>
                <NavItemContent>
                  <FontAwesomeIcon icon={faPersonRunning} />
                  Talent
                </NavItemContent>
              </Link>
            </NavItemContentWrapper>
          )}

          <Link to="/offer/1" style={{ textDecoration: 'none' }}>
            <NavItem>
              <FontAwesomeIcon icon={faHandPointRight} />
              Offerd Shells
            </NavItem>
          </Link>

          <Link to="/dm/1" style={{ textDecoration: 'none' }}>
            <NavItem>
              <FontAwesomeIcon icon={faMessage} />
              Message
            </NavItem>
          </Link>
          <Link to="/login" style={{ textDecoration: 'none' }}>
            <NavItem>
              <FontAwesomeIcon icon={faArrowRightToBracket} />
              Login
            </NavItem>
          </Link>
          <Link to="/member/1" style={{ textDecoration: 'none' }}>
            <NavItem>
              <Avatar avatartype={'Userimg'} />
              My page
            </NavItem>
          </Link>

          <Link to="/myshells/1" style={{ textDecoration: 'none' }}>
            <NavItem>
              <FontAwesomeIcon icon={faPeopleCarryBox} />
              My Shell
            </NavItem>
          </Link>
        </NavItems>
      </NavContainer>
    </NavWrapper>
  );
};

export default Nav;
