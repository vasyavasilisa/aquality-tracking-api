package main.controllers.Administration;

import main.controllers.BaseController;
import main.exceptions.AqualityException;
import main.exceptions.AqualityPermissionsException;
import main.model.db.dao.settings.AppSettingsDao;
import main.model.db.dao.settings.LdapDao;
import main.model.dto.settings.AppSettingsDto;
import main.model.dto.settings.LdapDto;
import main.model.dto.settings.UserDto;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.NotImplemented;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class AppSettingsController extends BaseController<AppSettingsDto> {
    private AppSettingsDao appSettingsDao;
    private LdapDao ldapDao;

    public AppSettingsController(UserDto user) {
        super(user);
        appSettingsDao = new AppSettingsDao();
        ldapDao = new LdapDao();
    }

    public LdapDto getLdap() throws AqualityException {
        if (baseUser.isAdmin()) {
            return ldapDao.getAll().get(0);
        } else {
            throw new AqualityPermissionsException("Account is not allowed to view LDAP Settings", baseUser);
        }
    }

    public AppSettingsDto getApp() throws AqualityException {
        return appSettingsDao.getAll().get(0);
    }

    public LdapDto create(LdapDto template) throws AqualityException {
        if (baseUser.isAdmin()) {
            template.setAdminSecret(template.getAdminSecret() == null || template.getAdminSecret().equals("")
                    ? ""
                    : hideAdminSecret(template.getAdminSecret()));
            return ldapDao.create(template);
        } else {
            throw new AqualityPermissionsException("Account is not allowed to update LDAP Settings", baseUser);
        }
    }


    @Override
    public AppSettingsDto create(AppSettingsDto template) throws AqualityException {
        if (baseUser.isAdmin()) {
            return appSettingsDao.create(template);
        } else {
            throw new AqualityPermissionsException("Account is not allowed to update Application Settings", baseUser);
        }
    }

    @Override
    @NotImplemented
    public List<AppSettingsDto> get(AppSettingsDto entity) throws AqualityException {
        throw new UnsupportedOperationException();
    }

    @Override
    @NotImplemented
    public boolean delete(AppSettingsDto entity) throws AqualityException {
        throw new UnsupportedOperationException();
    }

    public String getAdminSecret() throws AqualityException {
        Base64 base64 = new Base64();
        String level1 = StringUtils.newStringUtf8(base64.decode(getLdap().getAdminSecret()));
        level1 = level1.replace("YXNkamhmbGtqYXNkaGx", "");
        level1 = level1.replace("a2poYXNka2xqZmJka2phc2JmbGFzYmRmamtiYXNsZA", "");
        String level2 = StringUtils.newStringUtf8(base64.decode(level1));
        level2 = level2.replace("JmbGFzYmRmamtiYXNsZA", "");
        level2 = level2.replace("qYXNkaGxma2poYXNka2xqZmJka2", "");
        return level2;
    }

    private String hideAdminSecret(String secret) throws AqualityException {
        try {
            Base64 base64 = new Base64();
            secret = base64.encodeToString(("JmbGFzYmRmamtiYXNsZA" + secret + "qYXNkaGxma2poYXNka2xqZmJka2").getBytes("utf-8"));
            return base64.encodeToString(("YXNkamhmbGtqYXNkaGx" + secret + "a2poYXNka2xqZmJka2phc2JmbGFzYmRmamtiYXNsZA").getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AqualityException("Cannot hide Admin Secret.");
        }
    }
}
